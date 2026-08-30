package remi.coDSE.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MedicCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("medic")) {
            if (args.length != 1) {
                sender.sendMessage("Error: Incorrect amount of arguments provided (1 expected, " + args.length + " provided)");
                return false;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                return false;
            }

            double hp = player.getHealth();
            if  (hp <= Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()-32) {
                player.setHealth(player.getHealth()+32);
            }
            else {
                player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            }

            player.playSound(player.getLocation(), "minecraft:misc.medic", 0.6f, 1f);

            return true;
        }
        return false;
    }
}
