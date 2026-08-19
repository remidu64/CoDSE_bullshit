package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class FeatherCurseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("feathercurse")) {
            if (sender instanceof Player player) {

                PlayerData data = PlayerUtil.getPlayerData(player);

                data.setPerk(4);
                PlayerUtil.setPlayerData(player, data);

                PlayerUtil.ApplyPerk(4, player);

                CoDSE.getInstance().getConfig().set(player.getUniqueId() + ".perk", 4);
                CoDSE.getInstance().saveConfig();
                return true;

            } else {
                sender.sendMessage("cant give perk to a non-player");
                return false;
            }
        } return false;
    }
}
