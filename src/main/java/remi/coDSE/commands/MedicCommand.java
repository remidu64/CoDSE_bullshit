package remi.coDSE.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.utiliy.GeneralUtil;

import java.util.Objects;

public class MedicCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (command.getName().equalsIgnoreCase("medic")) {
            return false;
        }

        if (args.length != 1) {
            GeneralUtil.sendMessage(sender,"<color:#FF0000>ERROR: Incorrect amount of arguments (1 expected, %s provided)</color>".formatted(args.length));
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: Player %s not found</color>".formatted(args[0]));
            return false;
        }

        double playerHealth = player.getHealth();

        if  (playerHealth <= Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()-32) {
            player.setHealth(player.getHealth() + 32);
        }
        else {
            player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue());
        }

        player.playSound(player.getLocation(), "minecraft:misc.medic", 0.6f, 1f);

        return true;
    }
}
