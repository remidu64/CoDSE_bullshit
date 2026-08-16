package remi.coDSE.core;

import org.bukkit.plugin.java.JavaPlugin;
import remi.coDSE.commands.FeatherCurseCommand;
import remi.coDSE.commands.JuggernogCommand;
import remi.coDSE.commands.QuickReviveCommand;
import remi.coDSE.commands.StaminupCommand;
import remi.coDSE.events.GeneralEvents;
import remi.coDSE.data.SpawnData;

import java.util.logging.Logger;

public final class CoDSE extends JavaPlugin {

    private Logger logger = getLogger();

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

        logger.info("plugin is on :3");

    }

    @Override
    public void onDisable() {
        logger.info("yeetus deletus");
    }

    private void loadCommands() {
        logger.info("loading commands");
        getCommand("staminup").setExecutor(new StaminupCommand());
        getCommand("juggernog").setExecutor(new JuggernogCommand());
        getCommand("quickrevive").setExecutor(new QuickReviveCommand());
        getCommand("feathercurse").setExecutor(new FeatherCurseCommand());

    }

    private void loadEvents() {
        logger.info("loading events");
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
    }
}
