package remi.coDSE.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class GeneralEvents implements Listener {

    CoDSE plugin = CoDSE.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = new PlayerData();

        int saved_perk = plugin.getConfig().getInt(player.getName() + ".perk");

        if (saved_perk == 0) { // saved_perk being 0 means the player joined for the first time, gotta add config for em
            plugin.getConfig().set(player.getName() + ".perk", -1);
            plugin.saveConfig();
            data.setPerk(-1);
        } else {
            data.setPerk(saved_perk);
        }

        PlayerUtil.ApplyPerk(data.getPerk(), player);

        PlayerUtil.setPlayerData(player, data);

        String joinMsg = "%player% joined the hellhole" + ChatColor.YELLOW;
        joinMsg = joinMsg.replaceAll("%player%", player.getName());

        event.setJoinMessage(joinMsg);


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        PlayerUtil.setPlayerData(event.getPlayer(), null);
    } //clean up shit when the player leaves

    @EventHandler
    public void OnRespawn(PlayerRespawnEvent event) {
        new BukkitRunnable() { // gotta wait one (1) tick before so the player loads
            public void run() {
                Player player = event.getPlayer();
                player.addPotionEffect(PotionEffectType.SATURATION.createEffect(Integer.MAX_VALUE, 2));

                int perk = PlayerUtil.getPlayerData(player).getPerk();
                PlayerUtil.ApplyPerk(perk, player);

                player.setGameMode(GameMode.ADVENTURE);

                Location spawn =  new Location(player.getWorld(), 10000.0, 31, 10000.0);
                player.teleport(spawn);
            }
        }.runTaskLater(CoDSE.getInstance(), 1);

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer != null) { // victim can have a brain aneurysm, dont want that to cause a crash

            PlayerData killer_data = PlayerUtil.getPlayerData(killer);
            killer_data.setKills(killer_data.getKills() + 1);
            PlayerUtil.setPlayerData(killer, killer_data);

        }
        PlayerData victim_data = PlayerUtil.getPlayerData(victim);
        victim_data.setKills(0);

    }
}
