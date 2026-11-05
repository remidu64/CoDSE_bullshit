package remi.coDSE.core;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import remi.coDSE.commands.*;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class Bootstrapper implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(ReloadSpawnsCommand.createCommand());
            event.registrar().register(RespawnCommand.createCommand());
            event.registrar().register(SetKillstreakCommand.createCommand());
            event.registrar().register(MedicCommand.createCommand());
            event.registrar().register(AddSpawnCommand.createCommand());
            event.registrar().register(DeadshotDaiquiriTriggerCommand.createCommand());
            event.registrar().register(StoppingRoundsCommand.createCommand());
            event.registrar().register(PerkCommand.createCommand());
        });
    }
}
