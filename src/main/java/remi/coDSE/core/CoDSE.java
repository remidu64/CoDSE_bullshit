package remi.coDSE.core;

import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
import org.bukkit.plugin.java.JavaPlugin;
import remi.coDSE.commands.*;
import remi.coDSE.events.DamageEvents;
import remi.coDSE.events.DeathAndRespawnEvents;
import remi.coDSE.events.JoinAndLeaveEvents;
import remi.coDSE.utiliy.ScoreboardUtil;

import java.util.Objects;
import java.util.logging.Logger;

public final class CoDSE extends JavaPlugin {

    private final Logger logger = getLogger();

    public JoinAndLeaveEvents JLevents;
    public DeathAndRespawnEvents DRevents;

    private static CoDSE instance; // constructor bullshit
    public CoDSE() {
        instance = this;
    }
    public static CoDSE getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        loadCommands();
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

    private void loadCommands() {
        logger.info("loading commands");
        Objects.requireNonNull(getCommand("staminup")).setExecutor(new StaminupCommand());
        Objects.requireNonNull(getCommand("juggernog")).setExecutor(new JuggernogCommand());
        Objects.requireNonNull(getCommand("quickrevive")).setExecutor(new QuickReviveCommand());
        Objects.requireNonNull(getCommand("feathercurse")).setExecutor(new FeatherCurseCommand());
        Objects.requireNonNull(getCommand("addspawn")).setExecutor(new AddSpawnCommand());
        Objects.requireNonNull(getCommand("medic")).setExecutor(new MedicCommand());
        Objects.requireNonNull(getCommand("reloadspawns")).setExecutor(new ReloadSpawnsCommand());

    }

    private void loadEvents() {
        logger.info("loading events");

        this.JLevents = new JoinAndLeaveEvents();
        this.DRevents = new DeathAndRespawnEvents();

        getServer().getPluginManager().registerEvents(JLevents, this);
        getServer().getPluginManager().registerEvents(DRevents, this);
        getServer().getPluginManager().registerEvents(new DamageEvents(), this);
    }
}
