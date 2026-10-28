package remi.coDSE.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.Nullable;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;

public class DamageEvents implements Listener {
    private static Player playerDamager = null;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        @Nullable Entity Edamager = event.getDamageSource().getCausingEntity();

        if (Edamager instanceof Player) {
            playerDamager = (Player) Edamager;
            if(event.getEntity() instanceof ItemFrame && !playerDamager.getGameMode().isInvulnerable()) {
                event.setCancelled(true);
            }

            PlayerData data = PlayerUtil.getPlayerData(playerDamager);
            if (data.getStopping_rounds() > 0) {
                event.setDamage(event.getDamage() * 1.2);
                data.decrementStopping_Rounds();
                PlayerUtil.setPlayerData(playerDamager, data);
            }

            if (data.didDDTrigger()) {
                event.setDamage(event.getDamage() * 2);
                data.setDDTrigger(false);
                PlayerUtil.setPlayerData(playerDamager, data);
            }

        }
        if (event.getEntity() instanceof Player player) {

            double current_absorption = player.getAbsorptionAmount();

            if (current_absorption - event.getDamage() < 0.1 && current_absorption > 0 && event.getDamage() > 0.05) {
                player.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f);

                if (playerDamager != null) {
                    playerDamager.playSound(player.getLocation(), "minecraft:misc.no_more_plate", 2.5f, 1f); // we also want the player causing the damage to hear the sound
                }
            }
        }
    }
}
