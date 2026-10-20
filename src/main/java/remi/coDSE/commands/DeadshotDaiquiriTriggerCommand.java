package remi.coDSE.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class DeadshotDaiquiriTriggerCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (command.getName().equalsIgnoreCase("ddtrigger")) {
            if (args.length != 1) {
                sender.sendMessage("Error: Incorrect amount of arguments provided (1 expected, " + args.length + " provided)");
                return false;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                return false;
            }

            PlayerData data = PlayerUtil.getPlayerData(player);

            if (data.getPerk() == 5) {
                data.setDDTrigger(true);
                PlayerUtil.setPlayerData(player, data);
            }
            return true;
        }
        return false;
    }
}
