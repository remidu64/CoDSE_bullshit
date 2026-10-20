package remi.coDSE.utiliy;

import com.alonsoaliaga.alonsoleagues.api.AlonsoLeaguesAPI;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardUtil {

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
                Component.text().build(),
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#00ff00:#118c4f>Balance: %s P</gradient>", Economy.getMoneyExact(scoreboard.getPlayer().getUniqueId()))),
                Component.text().build(),
                MiniMessage.miniMessage().deserialize(PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "<gradient:#ff55ff:#aa00aa>%alonsoleagues_league%</gradient>")),
                MiniMessage.miniMessage().deserialize(PlaceholderAPI.setPlaceholders(scoreboard.getPlayer(), "<gradient:#ff55ff:#aa00aa>%alonsoleagues_progress_stripped% %alonsoleagues_progress_percent_format%</gradient>")),
                Component.text().build(),
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#ff5555:#aa0000>Killstreak: %s</gradient>", PlayerUtil.getPlayerData(scoreboard.getPlayer()).getKills()))
        );
    }
}
