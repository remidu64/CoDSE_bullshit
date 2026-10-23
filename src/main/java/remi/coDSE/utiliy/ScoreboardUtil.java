package remi.coDSE.utiliy;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardUtil {
    static final Component emptyLine = Component.text().build();

    public static final Map<UUID, FastBoard> scoreboards = new HashMap<>();

    public static FastBoard getScoreboard(Player player) {
        if(!scoreboards.containsKey(player.getUniqueId())) {
            FastBoard scoreboard = new FastBoard(player);
            scoreboards.put(player.getUniqueId(), scoreboard);
            return scoreboard;
        }
        return scoreboards.get(player.getUniqueId());
    }

    public static void SetScoreboard(Player player,  FastBoard scoreboard) {
        if(scoreboard == null) scoreboards.remove(player.getUniqueId());
        else scoreboards.put(player.getUniqueId(), scoreboard);
    }

    public static void UpdateScoreboard( FastBoard scoreboard) throws UserDoesNotExistException {
        scoreboard.updateLines(
                emptyLine,
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#00ff00:#118c4f>Balance: %s P</gradient>", Economy.getMoneyExact(scoreboard.getPlayer().getUniqueId()))),
                emptyLine,
                MiniMessage.miniMessage().deserialize("<color:#%s>%s</color>".formatted(PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "%alonsoleagues_league_color%").replace("§", "").replace("&", "").replace("x", ""), PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "%alonsoleagues_league%"))),
                MiniMessage.miniMessage().deserialize("<color:#%s>%s</color>".formatted(PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "%alonsoleagues_league_color%").replace("§", "").replace("&", "").replace("x", ""), PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "%alonsoleagues_progress_stripped%"))),
                emptyLine,
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#ff5555:#aa0000>Killstreak: %s</gradient>", PlayerUtil.getPlayerData(scoreboard.getPlayer()).getKills())),
                emptyLine,
                MiniMessage.miniMessage().deserialize("<gradient:#C9C9C9:#4A4A4A>S-Rounds: %s</gradient>".formatted(PlayerUtil.getPlayerData(scoreboard.getPlayer()).getStopping_rounds()))
        );
    }
}
