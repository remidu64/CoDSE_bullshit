package remi.coDSE.utiliy;

import com.alonsoaliaga.alonsoleagues.api.AlonsoLeaguesAPI;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
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
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#ff55ff:#aa00aa>%s</gradient>", GetFormattedRank(scoreboard.getPlayer()))),
                Component.text().build(),
                MiniMessage.miniMessage().deserialize(String.format("<gradient:#ff5555:#aa0000>Killstreak: %s</gradient>", PlayerUtil.getPlayerData(scoreboard.getPlayer()).getKills()))
        );
    }

    private static String GetFormattedRank(Player player) {
        String rawRank = AlonsoLeaguesAPI.getLeague(player.getUniqueId());
        StringBuilder rankBuilder = new StringBuilder();

        String[] Array = rawRank.split("-");

        switch (Array.length) {
            case 1:
                rankBuilder.append(StringUtils.capitalize(Array[0]));
                break;

            case 2:
                if (Array[0].equalsIgnoreCase("unranked")) {
                    rankBuilder.append(StringUtils.capitalize(Array[0])).append(" ");
                    rankBuilder.append(Array[1].replace("p", "Prestige "));
                    rankBuilder.append(RomanUtil.toRoman(Integer.parseInt(Array[1].replaceAll("[^\\d.]", ""))));
                } else {
                    rankBuilder.append(StringUtils.capitalize(Array[0])).append(" ");
                    rankBuilder.append(RomanUtil.toRoman(Integer.parseInt(Array[1])));
                }
            case 3:
                rankBuilder.append(StringUtils.capitalize(Array[0])).append(" ");
                rankBuilder.append(RomanUtil.toRoman(Integer.parseInt(Array[1]))).append(" ");
                rankBuilder.append(Array[2].replace("p", "Prestige "));
                rankBuilder.append(RomanUtil.toRoman(Integer.parseInt(Array[2].replaceAll("[^\\d.]", ""))));

        }

        return rankBuilder.toString();
    }
}
