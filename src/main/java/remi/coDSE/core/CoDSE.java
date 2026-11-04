package remi.coDSE.core;

import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import remi.coDSE.commands.*;
import remi.coDSE.events.DamageEvents;
import remi.coDSE.events.DeathAndRespawnEvents;
import remi.coDSE.events.JoinAndLeaveEvents;
import remi.coDSE.utiliy.ScoreboardUtil;

import java.util.logging.Logger;

public final class CoDSE extends JavaPlugin {

    private final Logger logger = getLogger();

    private static CoDSE instance; // constructor bullshit
    public CoDSE() {
        instance = this;
    }
    public static CoDSE getInstance() {
        return instance;
    }
    public static final Server server = Bukkit.getServer();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        loadEvents();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for(FastBoard scoreboard: ScoreboardUtil.scoreboards.values()) {
                try {
                    ScoreboardUtil.UpdateScoreboard(scoreboard);
                } catch (UserDoesNotExistException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 20);

        logger.info("plugin is on :3");

    }

    @Override
    public void onDisable() {
        logger.info("yeetus deletus");
    }

    private void loadEvents() {
        logger.info("loading events");
        getServer().getPluginManager().registerEvents(new JoinAndLeaveEvents(), this);
        getServer().getPluginManager().registerEvents(new DeathAndRespawnEvents(), this);
        getServer().getPluginManager().registerEvents(new DamageEvents(), this);
        logger.info("events loaded");
    }
}
