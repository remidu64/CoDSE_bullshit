package remi.coDSE.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.PlayerUtil;

public class StoppingRoundsCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (command.getName().equalsIgnoreCase("stoppingrounds")) {
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<color:#FFFF00>Usage: /stoppingrounds <player> <set/add> <integer></color>"));
            return false;
        }

        if (args.length != 3) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<color:#FF0000>ERROR: Incorrect amount of argument (3 expected, %s provided)</color>".formatted(args.length)));
            return false;
        }

            Player player = Bukkit.getPlayer(args[0]);

        if(player == null) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(String.format("<color:#FF0000>ERROR: Player %s not found</color>".formatted(args[0]))));
            return false;
           }

        if (GeneralUtil.isNotNumeric(args[2])) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<color:#FF0000>ERROR: 3rd argument '%s' isn't an integer</color>".formatted(args[2])));
            return false;
        }

        PlayerData data = PlayerUtil.getPlayerData(player);

        switch (args[1]) {
            case "add" -> {
                data.addStopping_rounds(Integer.parseInt(args[2]));
                return true;
            }

            case "set" -> {
                data.setStopping_rounds(Integer.parseInt(args[2]));
                return true;
            }

            default -> {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<color:#FF0000>ERROR: Expected argument 'add' or 'set', got '%s' insteac</color>".formatted(args[1])));
                return false;
            }

        }
    }
}
