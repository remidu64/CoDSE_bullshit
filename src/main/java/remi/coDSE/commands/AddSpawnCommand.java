package remi.coDSE.commands;



// to use this command, you provide the location of the new spawn in the arguments

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;

import java.util.List;

@SuppressWarnings("UnstableApiUsage") public class AddSpawnCommand{
    public static LiteralCommandNode<CommandSourceStack> createCommand(){
        return Commands.literal("addspawn")
                .then(Commands.argument("x", IntegerArgumentType.integer()))
                .then(Commands.argument("y", IntegerArgumentType.integer()))
                .then(Commands.argument("z", IntegerArgumentType.integer()))
                .executes(context -> AddSpawn(context, IntegerArgumentType.getInteger(context, "x"), IntegerArgumentType.getInteger(context, "y"), IntegerArgumentType.getInteger(context, "z")))
                .build();
    }

    private static int AddSpawn(final CommandContext<CommandSourceStack> context, final int x, final int y, final int z){
        final CommandSender sender = context.getSource().getSender();

        List<List<Integer>> SpawnLocations = SpawnUtil.getSpawnLocations();
        List<Integer> Spawn = List.of(x, z, y);

        assert SpawnLocations != null;
        SpawnLocations.add(Spawn);

        SpawnUtil.setSpawnLocations(SpawnLocations);

        GeneralUtil.sendMessage(sender, "<color:#00FF00>Added spawn location at %s %s %s</color>".formatted(x, y, z));

        return Command.SINGLE_SUCCESS;
    }
}

/*
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (!command.getName().equalsIgnoreCase("addspawn")) {
            return false;
        }

        if (args.length == 0) {
            GeneralUtil.sendMessage(sender, "<color:#FFFF00>Usage: /addspawn <x> <y> <z> (x, y and z must be integers)</color>");
            return false;
        }

        if (args.length != 3) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: Incorrect amount of arguments (3 expected, %s provided)</color>".formatted(args.length));
            return false;
        }

        boolean argumentsInvalid = false;

        if (GeneralUtil.isNotNumeric(args[0])) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: 1st argument '%s' isn't an integer</color>".formatted(args[0]));
            argumentsInvalid = true;
        }

        if (GeneralUtil.isNotNumeric(args[1])) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: 2nd argument '%s' isn't an integer</color>".formatted(args[1]));
            argumentsInvalid = true;
        }

        if (GeneralUtil.isNotNumeric(args[2])) {
            GeneralUtil.sendMessage(sender, "<color:#FF0000>ERROR: 3rd argument '%s' isn't an integer</color>".formatted(args[2]));
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

        GeneralUtil.sendMessage(sender, "<color:#00FF00>Added spawn location at %s %s %s</color>".formatted(args[0], args[1], args[2]));

        return true;

    }
*/
