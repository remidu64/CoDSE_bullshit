package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

@SuppressWarnings("UnstableApiUsage") public class StoppingRoundsCommand{
    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("stoppingrounds")
                .then(Commands.argument("target", ArgumentTypes.player())
                        .then(Commands.literal("set")
                                .then(Commands.argument("amount", IntegerArgumentType.integer())))
                        .executes(context -> SetStoppingRounds(context, IntegerArgumentType.getInteger(context, "amount")))
                        .then(Commands.literal("add")
                                .then(Commands.argument("amount", IntegerArgumentType.integer())))
                        .executes(context ->  AddStoppingRounds(context, IntegerArgumentType.getInteger(context, "amount")))
                ).build();
    }

    private static int SetStoppingRounds(final CommandContext<CommandSourceStack> context, final int amount) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final PlayerData data = PlayerUtil.getPlayerData(target);

        data.setStopping_rounds(amount);

        return Command.SINGLE_SUCCESS;
    }

    private static int AddStoppingRounds(final CommandContext<CommandSourceStack> context, final int amount) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final PlayerData data = PlayerUtil.getPlayerData(target);

        data.addStopping_rounds(amount);

        return Command.SINGLE_SUCCESS;
    }
}