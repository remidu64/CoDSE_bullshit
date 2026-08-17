package remi.coDSE.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;
import remi.coDSE.utiliy.SpawnUtil;

import java.util.List;
import java.util.Random;

public class GeneralEvents implements Listener {
    CoDSE plugin = CoDSE.getInstance();

    // type-safe casting ? i barely know her !
    @SuppressWarnings("unchecked") private List<List<Integer>> SpawnLocations = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    private int SpawnListSize;
    {
        assert SpawnLocations != null;
        SpawnListSize = SpawnLocations.size();
    }

    public List<List<Integer>> getSpawnLocations() {
        return SpawnLocations;
    }

    public void setSpawnLocations(List<List<Integer>> SpawnLocations) {
        this.SpawnLocations = SpawnLocations;
        this.SpawnListSize = SpawnLocations.size();
        plugin.getConfig().set("spawns", SpawnLocations);
        plugin.saveConfig();
    }


    Random random = new Random();

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

        String joinMsg =  ChatColor.YELLOW +"%player% joined the hellhole";
        joinMsg = joinMsg.replaceAll("%player%", player.getName());

        event.setJoinMessage(joinMsg);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        PlayerUtil.setPlayerData(event.getPlayer(), null);
    } //clean up shit when the player leaves

    @EventHandler
    public void OnRespawn(PlayerRespawnEvent event) {
        new BukkitRunnable() { // gotta wait one (1) tick so the player loads
            public void run() {
                Player player = event.getPlayer();

                int perk = PlayerUtil.getPlayerData(player).getPerk();
                PlayerUtil.ApplyPerk(perk, player);

                player.setGameMode(GameMode.ADVENTURE);

                List<Integer> SpawnLocation = SpawnLocations.get(random.nextInt(SpawnListSize));
                SpawnUtil.Respawn(player, SpawnLocation.get(0) + 0.5, SpawnLocation.get(1), SpawnLocation.get(2) + 0.5);


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
