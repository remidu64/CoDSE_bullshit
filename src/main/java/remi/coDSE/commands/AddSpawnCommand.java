package remi.coDSE.commands;



// to use this command, you provide the location of the new spawn in the arguments

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
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
