package remi.coDSE.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.PlayerUtil;

@SuppressWarnings("UnstableApiUsage") public class PerkCommand {
    static final CoDSE plugin = CoDSE.getInstance();

    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("perk")
                .then(Commands.literal("staminup")
                        .executes(context -> SetPerk(context, 1, "Staminup")))
                .then(Commands.literal("quickrevive")
                        .executes(context -> SetPerk(context, 2, "Quick Revive")))
                .then(Commands.literal("juggernog")
                        .executes(context -> SetPerk(context, 3, "Juggernog")))
                .then(Commands.literal("featherscurse")
                        .executes(context -> SetPerk(context, 4, "Feather's Curse ")))
                .then(Commands.literal("deadshotdaiquiri")
                        .executes(context -> SetPerk(context, 5, "DeadShot Daiquiri")))
                .build();
    }

    @SuppressWarnings("SameReturnValue") private static int SetPerk(final CommandContext<CommandSourceStack> context, final int perk, final String perk_name){
        final CommandSender sender = context.getSource().getSender();

        if(!(sender instanceof Player player)) {
            GeneralUtil.sendMessage(sender, "<color:#ff0000>You must be a player to use this command.</color>");
            return Command.SINGLE_SUCCESS;
        }

        final PlayerData data = PlayerUtil.getPlayerData(player);
        data.setPerk(perk);

        PlayerUtil.setPlayerData(player, data);

        PlayerUtil.ApplyPerk(perk, player);

        plugin.getConfig().set(player.getUniqueId() + ".perk", perk);
        plugin.saveConfig();

        GeneralUtil.sendMessage(player, "<color:#00ff00>Your perk is now %s</color>".formatted(perk_name));

        return Command.SINGLE_SUCCESS;
    }
}
