package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.PlayerUtil;

public class FeatherCurseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {

        if (!command.getName().equalsIgnoreCase("feathercurse")) {
            return false;
        }

        if (!(sender instanceof Player player)) {
            GeneralUtil.messageShortcut(sender,"<color:#FF0000>ERROR: Command sender is not a player</color>");
            return false;
        }

        PlayerData data = PlayerUtil.getPlayerData(player);

        data.setPerk(4);
        PlayerUtil.setPlayerData(player, data);

        PlayerUtil.ApplyPerk(4, player);

        CoDSE.getInstance().getConfig().set(player.getUniqueId() + ".perk", 4);
        CoDSE.getInstance().saveConfig();
        return true;

    }
}
