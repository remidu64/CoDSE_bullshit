package remi.coDSE.utiliy;


import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnUtil {

    public static void Respawn(Player player, double x, double y, double z) {
        player.teleport(new Location(player.getWorld(), x, y, z));
    }
}
