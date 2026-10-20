package remi.coDSE.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class DamageEvents implements Listener {
    static Player playerdamager = null;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        @Nullable Entity Edamager = event.getDamageSource().getCausingEntity();

        if (Edamager instanceof Player) {
            playerdamager  = (Player) Edamager;
            if(event.getEntity() instanceof ItemFrame && !playerdamager.getGameMode().isInvulnerable()) {
                event.setCancelled(true);
            }
            PlayerData data = PlayerUtil.getPlayerData(playerdamager);
            if (data.getStopping_rounds() > 0) {
                event.setDamage(event.getDamage() * 1.2);
                data.decrementStopping_Rounds();
                PlayerUtil.setPlayerData(playerdamager, data);
            }

            if (data.didDDTrigger()) {
                event.setDamage(event.getDamage() * 2);
                data.setDDTrigger(false);
                PlayerUtil.setPlayerData(playerdamager, data);
            }

        } else {
            playerdamager = null;
        }
        if (event.getEntity() instanceof Player player) {

            double current_absorption = player.getAbsorptionAmount();
            new BukkitRunnable() {
                public void run() {
                    double next_absorption = player.getAbsorptionAmount();

                    if (next_absorption < 0.1 && current_absorption > 0 && next_absorption != current_absorption) {
                        player.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f);

                        if (playerdamager != null) {
                            playerdamager.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f); // we also want the player causing the damage to hear the sound
                        }
                    }
                }
            }.runTask(CoDSE.getInstance());
        }
    }
}
