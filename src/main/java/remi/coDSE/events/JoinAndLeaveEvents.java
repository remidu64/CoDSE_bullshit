package remi.coDSE.events;

import fr.mrmicky.fastboard.adventure.FastBoard;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
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
        scoreboard.updateTitle(Component.text().content("CODSE").color(TextColor.fromHexString("#ffff00")).decorate(TextDecoration.BOLD).build());
        ScoreboardUtil.SetScoreboard(player, scoreboard);

        event.joinMessage(Component.text(player.getName() + " joined the hellhole", TextColor.color(255, 255, 0)));

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        //clean up shit when the player leaves
        Player player = event.getPlayer();

        PlayerUtil.setPlayerData(player, null);
        ScoreboardUtil.SetScoreboard(player, null);


    }

}
