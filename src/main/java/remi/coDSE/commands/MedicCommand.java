package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

@SuppressWarnings("UnstableApiUsage") public class MedicCommand{
    public static final LiteralCommandNode<CommandSourceStack> Medic = Commands.literal("medic")
                .then(Commands.argument("target", ArgumentTypes.player()))
                .executes(MedicCommand::MedicLogic)
                .build();

    private static int MedicLogic(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver resolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
        final Player target = resolver.resolve(context.getSource()).getFirst();
        final double playerHealth = target.getHealth();

        if  (playerHealth <= Objects.requireNonNull(target.getAttribute(Attribute.MAX_HEALTH)).getValue()-32) {
            target.setHealth(target.getHealth() + 32);
        }
        else {
            target.setHealth(Objects.requireNonNull(target.getAttribute(Attribute.MAX_HEALTH)).getValue());
        }

        target.playSound(target.getLocation(), "minecraft:misc.medic", 0.6f, 1f);

        return Command.SINGLE_SUCCESS;
    }
}