package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import remi.coDSE.core.CoDSE;


public class ReloadSpawnsCommand implements CommandExecutor {
    CoDSE plugin = CoDSE.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadspawns")) {

            plugin.events.reloadSpawnLocations();

            return true;

        } return false;
    }

}
