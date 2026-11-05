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
            event.registrar().register(ReloadSpawnsCommand.ReloadSpawns);
            event.registrar().register(RespawnCommand.Respawn);
            event.registrar().register(SetKillstreakCommand.SetKillstreak);
            event.registrar().register(MedicCommand.Medic);
            event.registrar().register(AddSpawnCommand.AddSpawn);
            event.registrar().register(DeadshotDaiquiriTriggerCommand.DeadshotDaiquiriTrigger);
            event.registrar().register(StoppingRoundsCommand.StoppingRounds);
            event.registrar().register(PerkCommand.Perk);
        });
    }
}
