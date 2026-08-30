package remi.coDSE.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;
import remi.coDSE.utiliy.SpawnUtil;

import java.util.List;


// to use this command, you provide the location of the new spawn in the arguments

public class AddSpawnCommand implements CommandExecutor {
    CoDSE plugin = CoDSE.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("addspawn")) {
            if (args.length != 3) {
                sender.sendMessage(Component.text("Error: Incorrect amount of arguments provided (3 expected, " + args.length + " provided)", TextColor.color(255, 0, 0)));
                return false;
            }

            List<List<Integer>> SpawnLocations = SpawnUtil.getSpawnLocations();
            List<Integer> Spawn = List.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

            assert SpawnLocations != null;
            SpawnLocations.add(Spawn);

            SpawnUtil.setSpawnLocations(SpawnLocations);

            sender.sendMessage(Component.text("Successfully added spawn location at " + Spawn, TextColor.color(0, 255, 0)));

            return true;

        } return false;
    }
}
