package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;

import java.util.List;


// to use this command, you provide the location of the new spawn in the arguments

public class AddSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (!command.getName().equalsIgnoreCase("addspawn")) {
            return false;
        }

        if (args.length == 0) {
            GeneralUtil.messageShortcut(sender, "<color:#FFFF00>Usage: /addspawn <x> <y> <z> (x, y and z must be integers)</color>");
            return false;
        }

        if (args.length != 3) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: Incorrect amount of arguments (3 expected, %s provided)</color>".formatted(args.length));
            return false;
        }

        boolean argumentsInvalid = false;

        if (GeneralUtil.isNotNumeric(args[0])) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: 1st argument '%s' isn't an integer</color>".formatted(args[0]));
            argumentsInvalid = true;
        }

        if (GeneralUtil.isNotNumeric(args[1])) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: 2nd argument '%s' isn't an integer</color>".formatted(args[1]));
            argumentsInvalid = true;
        }

        if (GeneralUtil.isNotNumeric(args[2])) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: 3rd argument '%s' isn't an integer</color>".formatted(args[2]));
            argumentsInvalid = true;
        }

        if (argumentsInvalid) {
            return false;
        }

        List<List<Integer>> SpawnLocations = SpawnUtil.getSpawnLocations();
        List<Integer> Spawn = List.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        assert SpawnLocations != null;
        SpawnLocations.add(Spawn);

        SpawnUtil.setSpawnLocations(SpawnLocations);

        GeneralUtil.messageShortcut(sender, "<color:#00FF00>Added spawn location at %s %s %s</color>".formatted(args[0], args[1], args[2]));

        return true;

    }
}
