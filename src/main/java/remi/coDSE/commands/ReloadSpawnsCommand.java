package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;


@SuppressWarnings("UnstableApiUsage") public class ReloadSpawnsCommand {
    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("reloadspawns")
                .executes(ReloadSpawnsCommand::ReloadSpawns)
                .build();
    }

    private static int ReloadSpawns(final CommandContext<CommandSourceStack> context){
        final CommandSender sender = context.getSource().getSender();

        SpawnUtil.reloadSpawnLocations();
        GeneralUtil.sendMessage(sender, "<color:#FF0000>Reloaded spawn locations</color>");

        return Command.SINGLE_SUCCESS;
    }
}