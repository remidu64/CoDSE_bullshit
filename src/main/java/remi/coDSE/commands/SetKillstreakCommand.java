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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.KillUtil;
import remi.coDSE.utiliy.PlayerUtil;

@SuppressWarnings("UnstableApiUsage") public class SetKillstreakCommand{
    public static final LiteralCommandNode<CommandSourceStack> SetKillstreak = Commands.literal("setkillstreak")
                .then(Commands.argument("target", ArgumentTypes.player())
                    .then(Commands.argument("amount", IntegerArgumentType.integer())
                        .executes(context -> SetKillstreakLogic(context, IntegerArgumentType.getInteger(context, "amount")))))
                .build();

    private static int SetKillstreakLogic(final CommandContext<CommandSourceStack> context, final int amount) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final PlayerData data = PlayerUtil.getPlayerData(target);
        final CommandSender sender = context.getSource().getSender();

        data.setKills(amount);
        PlayerUtil.setPlayerData(target, data);

        KillUtil.HandlePointsAndShit(target, amount);

        GeneralUtil.sendMessage(sender, "<color:#00FF00>Set %s's killstreak to %s</color>".formatted(target.getName(), amount));
        return Command.SINGLE_SUCCESS;
    }
}
