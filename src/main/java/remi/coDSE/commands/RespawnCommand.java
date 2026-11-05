package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;

@SuppressWarnings("UnstableApiUsage") public class RespawnCommand{
    public static final LiteralCommandNode<CommandSourceStack> Respawn = Commands.literal("respawn")
                .then(Commands.argument("target", ArgumentTypes.player()))
                .executes(RespawnCommand::RespawnPlayerLogic)
                .build();

    private static int RespawnPlayerLogic(final CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final CommandSender sender = context.getSource().getSender();

        SpawnUtil.Respawn(target);
        GeneralUtil.sendMessage(sender, "<color:#00FF00>Respawned %s </color>".formatted(target.getName()));
        return Command.SINGLE_SUCCESS;
    }
}
