package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;


public class ReloadSpawnsCommand implements CommandExecutor {
    CoDSE plugin = CoDSE.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadspawns")) {

            plugin.DRevents.reloadSpawnLocations();

            return true;

        } return false;
    }

}
