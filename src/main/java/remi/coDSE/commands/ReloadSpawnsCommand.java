package remi.coDSE.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.utiliy.SpawnUtil;


public class ReloadSpawnsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadspawns")) {

            SpawnUtil.reloadSpawnLocations();
            sender.sendMessage(Component.text("Reloaded spawn locations", TextColor.color(0, 255, 0)));

            return true;

        } return false;
    }

}
