package remi.coDSE.commands;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.KillUtil;
import remi.coDSE.utiliy.PlayerUtil;

public class SetKillstreakCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (!command.getName().equalsIgnoreCase("setkillstreak")) {
            return false;
        }

        if (args.length == 0) {
            GeneralUtil.messageShortcut(sender, "Usage: /setkillstreak <player> <amount>");
            return false;
        }

        if (args.length != 2) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: Incorrect amount of arguments (2 expected, %s found)</color>".formatted(args.length));
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: Player %s not found</color>".formatted(args[0]));
            return false;
        }

        if (GeneralUtil.isNotNumeric(args[1])) {
            GeneralUtil.messageShortcut(sender, "<color:#FF0000>ERROR: 2nd argument '%s' isn't an integer</color>".formatted(args[1]));
            return false;
        }

        int kill = Integer.parseInt(args[1]);

        PlayerData data = PlayerUtil.getPlayerData(player);
        data.setKills(kill);
        PlayerUtil.setPlayerData(player, data);
        try {
            KillUtil.HandlePointsAndShit(player, kill);
        } catch (MaxMoneyException | NoLoanPermittedException | UserDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        GeneralUtil.messageShortcut(sender, "<color:#00FF00>Set %s's killstreak to %s</color>".formatted(args[0], kill));
        return true;
    }
}
