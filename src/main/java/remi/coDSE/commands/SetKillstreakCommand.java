package remi.coDSE.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class SetKillstreakCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setkillstreak")) {
            if (args.length == 2) {
                Player player = Bukkit.getPlayer(args[0]);

                if (player == null) {
                    sender.sendMessage(Component.text("Error: Player " + args[0] + " doesn't exist", TextColor.color(255, 0, 0)));
                    return false;
                }

                int kill;
                try {
                    kill = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(Component.text("Error: " + args[1] +  " is not a positive integer", TextColor.color(255, 0, 0)));
                    return false;
                }

                PlayerData data = PlayerUtil.getPlayerData(player);
                data.setKills(kill);
                PlayerUtil.setPlayerData(player, data);
                sender.sendMessage(Component.text("Successfully set " + args[0] + "'s killstreak to " + args[1], TextColor.color(0, 255, 0)));

                return true;
            } else {
                sender.sendMessage(Component.text("Error: Incorrect amount of arguments provided (2 expected, " + args.length + " provided)", TextColor.color(255, 0, 0)));
                return false;
            }

        }
        return false;
    }
}
