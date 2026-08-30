package remi.coDSE.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.utiliy.SpawnUtil;

public class RespawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player;
        switch (args.length) {
            case 0:
                if (sender instanceof Player ) {
                    player = (Player) sender;
                    SpawnUtil.Respawn(player);
                    return true;
                }
                sender.sendMessage("Must be a player to use this command without arguments");
                return false;
            case 1:
                player = Bukkit.getPlayer(args[0]);
                if (player != null) {
                    SpawnUtil.Respawn(player);
                    return true;
                }
                sender.sendMessage("Player specified does not exist or is not online");
                return false;
            default:
                sender.sendMessage("Incorrect number of arguments");
                return false;
        }
    }
}
