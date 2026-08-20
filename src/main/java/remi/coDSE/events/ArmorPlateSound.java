package remi.coDSE.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import remi.coDSE.core.CoDSE;

public class ArmorPlateSound implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {

            double current_absorption = player.getAbsorptionAmount();
            new BukkitRunnable() {
                public void run() {
                    double next_absorption = player.getAbsorptionAmount();

                    player.sendMessage(current_absorption + " -> " + next_absorption);

                    if(next_absorption < 0.1 && current_absorption > 0 && next_absorption != current_absorption) { // did the absorption go below 0.1
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f);
                        }
                    }
                }
            }.runTask(CoDSE.getInstance());
        }
    }
}
