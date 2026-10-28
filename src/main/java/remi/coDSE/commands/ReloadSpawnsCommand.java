package remi.coDSE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.SpawnUtil;


public class ReloadSpawnsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String @NonNull [] args) {
        if (command.getName().equalsIgnoreCase("reloadspawns")) {
            return false;
        }

        SpawnUtil.reloadSpawnLocations();
        GeneralUtil.messageShortcut(sender, "<color:#FF0000>Reloaded spawn locations</color>");

        return true;

    }
}
