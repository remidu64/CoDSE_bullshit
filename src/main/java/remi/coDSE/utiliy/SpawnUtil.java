package remi.coDSE.utiliy;


import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;

import java.util.List;
import java.util.Random;

public class SpawnUtil {
    static CoDSE plugin = CoDSE.getInstance();
    static Random random = new Random();

    // type-safe casting ? i barely know her !
    @SuppressWarnings("unchecked") public static List<List<Integer>> SpawnLocations = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    public static int SpawnListSize;

    static {
        assert SpawnLocations != null;
        SpawnListSize = SpawnLocations.size();
    }

    public static List<List<Integer>> getSpawnLocations() {
        return SpawnLocations;
    }

    public static void setSpawnLocations(@NotNull List<List<Integer>> NewSpawnLocations) {
        SpawnListSize = NewSpawnLocations.size();
        plugin.getConfig().set("spawns", NewSpawnLocations);
        plugin.saveConfig();
    }
    public static void reloadSpawnLocations() {
        SpawnLocations = (List<List<Integer>>) plugin.getConfig().getList("spawns");
    }

    public static void Respawn(Player player) {
        player.setGameMode(GameMode.ADVENTURE);

        int perk = PlayerUtil.getPlayerData(player).getPerk();

        List<Integer> SpawnLocation = SpawnLocations.get(random.nextInt(SpawnListSize));
        new BukkitRunnable() { // gotta wait one (1) tick so the player loads
            public void run() {
                player.teleport(new Location(player.getWorld(), SpawnLocation.get(0), SpawnLocation.get(1), SpawnLocation.get(2)));
                PlayerUtil.ApplyPerk(perk, player);
            }
        }.runTask(CoDSE.getInstance());
    }
}
