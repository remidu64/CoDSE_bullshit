package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

@SuppressWarnings("UnstableApiUsage") public class DeadshotDaiquiriTriggerCommand{
    public static final LiteralCommandNode<CommandSourceStack> DeadshotDaiquiriTrigger = Commands.literal("ddtrigger")
                .then(Commands.argument("target", ArgumentTypes.player()))
                .executes(DeadshotDaiquiriTriggerCommand::DeadshotDaiquiriTriggerLogic)
                .build();

    private static int DeadshotDaiquiriTriggerLogic(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final PlayerData data = PlayerUtil.getPlayerData(target);

        if (data.getPerk() == 5) {
            data.setDDTrigger(true);
            PlayerUtil.setPlayerData(target, data);
        }

        return Command.SINGLE_SUCCESS;
    }
}