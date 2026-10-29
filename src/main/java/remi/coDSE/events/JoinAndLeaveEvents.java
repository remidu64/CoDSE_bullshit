package remi.coDSE.events;

import fr.mrmicky.fastboard.adventure.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.GeneralUtil;
import remi.coDSE.utiliy.PlayerUtil;
import remi.coDSE.utiliy.ScoreboardUtil;

// this class handles events related to joining and leaving the server

public class JoinAndLeaveEvents implements Listener {
    CoDSE plugin = CoDSE.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = new PlayerData();

        int saved_perk = plugin.getConfig().getInt(player.getUniqueId() + ".perk");

        if (saved_perk == 0) { // saved_perk being 0 means the player joined for the first time, gotta add config for em
            plugin.getConfig().set(player.getUniqueId() + ".perk", -1);
            plugin.saveConfig();
            data.setPerk(-1);
        } else {
            data.setPerk(saved_perk);
        }

        PlayerUtil.ApplyPerk(data.getPerk(), player);

        PlayerUtil.setPlayerData(player, data);

        FastBoard scoreboard = new FastBoard(player);
        scoreboard.updateTitle(GeneralUtil.deserialize("<color:#FFFF00><b>CODSE</b></color>"));
        ScoreboardUtil.SetScoreboard(player, scoreboard);

        event.joinMessage(GeneralUtil.deserialize("<color:#FFFF00>%s has joined the hellhole</color>".formatted(player.displayName())));

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        //clean up shit when the player leaves
        Player player = event.getPlayer();
        event.quitMessage(GeneralUtil.deserialize("<color:#FFFF00>%s is gonna touch grass</color>".formatted(player.displayName())));

        PlayerUtil.setPlayerData(player, null);
        ScoreboardUtil.SetScoreboard(player, null);


    }

}
