package remi.coDSE.core;

import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.mrmicky.fastboard.adventure.FastBoard;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import remi.coDSE.commands.*;
import remi.coDSE.events.DamageEvents;
import remi.coDSE.events.DeathAndRespawnEvents;
import remi.coDSE.events.JoinAndLeaveEvents;
import remi.coDSE.utiliy.ScoreboardUtil;

public final class CoDSE extends JavaPlugin {

    private void Log(final String log) {
        this.getSLF4JLogger().debug(log);
    }

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
        loadCommands();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for(final FastBoard scoreboard: ScoreboardUtil.scoreboards.values()) {
                try {
                    ScoreboardUtil.UpdateScoreboard(scoreboard);
                } catch (UserDoesNotExistException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 20);

        Log("plugin is on :3");

    }

    @Override
    public void onDisable() { Log("yeetus deletus");}

    private void loadEvents() {
        Log("loading events");
        getServer().getPluginManager().registerEvents(new JoinAndLeaveEvents(), this);
        getServer().getPluginManager().registerEvents(new DeathAndRespawnEvents(), this);
        getServer().getPluginManager().registerEvents(new DamageEvents(), this);
        Log("events loaded");
    }

    @SuppressWarnings("UnstableApiUsage") private void loadCommands() {
        Log("loading late commands");
        final LifecycleEventManager<Plugin> lifecycleManager = this.getLifecycleManager();

        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(SetKillstreakCommand.SetKillstreak);
            event.registrar().register(PerkCommand.Perk);
            event.registrar().register(ReloadSpawnsCommand.ReloadSpawns);
            event.registrar().register(RespawnCommand.Respawn);
            event.registrar().register(MedicCommand.Medic);
            event.registrar().register(AddSpawnCommand.AddSpawn);
            event.registrar().register(DeadshotDaiquiriTriggerCommand.DeadshotDaiquiriTrigger);
            event.registrar().register(StoppingRoundsCommand.StoppingRounds);
        });
        Log("loaded late commands");
    }
}
