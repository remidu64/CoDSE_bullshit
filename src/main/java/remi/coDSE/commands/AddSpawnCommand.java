package remi.coDSE.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import remi.coDSE.core.CoDSE;

import java.util.List;


// to use this command, you provide the location of the new spawn in the arguments

public class AddSpawnCommand implements CommandExecutor {
    CoDSE plugin = CoDSE.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("addspawn")) {
            if (args.length != 3) {
                sender.sendMessage(ChatColor.RED + "Error: Incorrect amount of arguments provided (3 expected, " + args.length + " provided)");
                return false;
            }

            List<List<Integer>> SpawnLocations = plugin.events.getSpawnLocations();
            List<Integer> Spawn = List.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

            assert SpawnLocations != null;
            SpawnLocations.add(Spawn);

            plugin.events.setSpawnLocations(SpawnLocations);

            sender.sendMessage(ChatColor.GREEN + "Successfully added spawn location at " + Spawn);

            return true;

        } return false;
    }
}
