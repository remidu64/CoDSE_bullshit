package remi.coDSE.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import remi.coDSE.core.CoDSE;

public class DamageEvents implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {

            double current_absorption = player.getAbsorptionAmount();
            new BukkitRunnable() {
                public void run() {
                    double next_absorption = player.getAbsorptionAmount();

                    if (next_absorption < 0.1 && current_absorption > 0 && next_absorption != current_absorption) {
                        player.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f);

                        if (event.getDamageSource().getCausingEntity() instanceof Player cause) {
                            cause.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f); // we also want the player causing the damage to hear the sound
                        }
                    }
                }
            }.runTask(CoDSE.getInstance());
        }
    }
}
