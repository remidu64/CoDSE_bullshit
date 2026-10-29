package remi.coDSE.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;

public class RespawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NonNull [] args) {
        if (!command.getName().equalsIgnoreCase("respawn")) {
            return false;
        }

        if (args.length == 0) {
            GeneralUtil.sendMessage(sender, "<color:#FFFF00>Usage: /respawn <player></color>");
            return false;
        }

        if (args.length != 1) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: Incorrect amount of arguments (1 expected, %s found)</color>".formatted(args.length));
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: Player %s not found</color>".formatted(args[0]));
            return false;
        }

        SpawnUtil.Respawn(player);
        GeneralUtil.sendMessage(sender, "<color:#00FF00>Respawned %s </color>".formatted(args[0]));
        return true;
    }
}
