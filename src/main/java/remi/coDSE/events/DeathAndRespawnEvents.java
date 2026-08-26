package remi.coDSE.events;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;
import remi.coDSE.utiliy.SpawnUtil;
import remi.coDSE.utiliy.KillUtil;

import java.util.List;
import java.util.Random;

// this class handles events related to dying and respawning

public class DeathAndRespawnEvents implements Listener {
    CoDSE plugin = CoDSE.getInstance();

    // type-safe casting ? i barely know her !
    @SuppressWarnings("unchecked") private List<List<Integer>> SpawnLocations = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    private int SpawnListSize;
    {
        KillUtil.SetupArmor();
        assert SpawnLocations != null;
        SpawnListSize = SpawnLocations.size();
    }

    public List<List<Integer>> getSpawnLocations() {
        return SpawnLocations;
    }

    public void setSpawnLocations(@NotNull List<List<Integer>> SpawnLocations) {
        this.SpawnLocations = SpawnLocations;
        this.SpawnListSize = SpawnLocations.size();
        plugin.getConfig().set("spawns", SpawnLocations);
        plugin.saveConfig();
    }
    public void reloadSpawnLocations() {
        this.SpawnLocations = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    }


    Random random = new Random();

    @EventHandler
    public void OnRespawn(@NotNull PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        int perk = PlayerUtil.getPlayerData(player).getPerk();


        player.setGameMode(GameMode.ADVENTURE);

        List<Integer> SpawnLocation = SpawnLocations.get(random.nextInt(SpawnListSize));
        new BukkitRunnable() { // gotta wait one (1) tick so the player loads
            public void run() {
                SpawnUtil.Respawn(player, SpawnLocation.get(0) + 0.5, SpawnLocation.get(1), SpawnLocation.get(2) + 0.5);
                PlayerUtil.ApplyPerk(perk, player);
            }
        }.runTask(CoDSE.getInstance());

    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer != null && killer != victim) { // victim can have a brain aneurysm, dont want that to cause a crash
            PlayerData killer_data = PlayerUtil.getPlayerData(killer);
            killer_data.incrementKills();
            PlayerUtil.setPlayerData(killer, killer_data);
            KillUtil.HandlePointsAndShit(killer, killer_data.getKills());
        }
        PlayerData victim_data = PlayerUtil.getPlayerData(victim);
        victim_data.setKills(0);

    }
}
