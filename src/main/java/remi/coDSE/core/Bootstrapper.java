package remi.coDSE.core;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import remi.coDSE.commands.ReloadSpawnsCommand;
import remi.coDSE.commands.RespawnCommand;

@SuppressWarnings("UnstableApiUsage")
public class Bootstrapper implements PluginBootstrap {

    @Override @SuppressWarnings("UnstableApiUsage")
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(ReloadSpawnsCommand.createCommand());
            event.registrar().register(RespawnCommand.createCommand());
        });
    }
}
