package remi.coDSE.events;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PhasetwoUtil;
import remi.coDSE.utiliy.PlayerUtil;
import remi.coDSE.utiliy.SpawnUtil;
import remi.coDSE.utiliy.KillUtil;
import java.util.Random;

// this class handles events related to dying and respawning

public class DeathAndRespawnEvents implements Listener {
    static final Random random = new Random();

    @EventHandler
    public void OnRespawn(@NotNull PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        if(PlayerUtil.getPlayerData(player).isPhase2()) {
            Location location = PlayerUtil.getPlayerData(player).getLocation();
            Bukkit.getServer().getScheduler().runTask(CoDSE.getInstance(), () -> {
                player.teleport(location);
                PhasetwoUtil.Phase2(player);
            });
            return;
        }
        SpawnUtil.Respawn(player);

    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
        final Player victim = event.getEntity();
        victim.stopSound("meme.encounter");
        final PlayerData victim_data = PlayerUtil.getPlayerData(victim);

        if(random.nextInt(5) == 0 && !victim_data.isPhase2() && victim_data.getKills() >= 10) {
            Location location = victim.getLocation();
            victim_data.setPhase2(true);
            victim_data.setLocation(location);
            return;
        }

        victim_data.setPhase2(false);
        final Player killer = victim.getKiller();
        if (killer != null && killer != victim) {
            // victim can have a brain aneurysm, dont want that to cause a crash
            final PlayerData killer_data = PlayerUtil.getPlayerData(killer);
            killer_data.incrementKills();
            PlayerUtil.setPlayerData(killer, killer_data);
            KillUtil.HandlePointsAndShit(killer, killer_data.getKills());
        }

        victim_data.setKills(0);
        PlayerUtil.setPlayerData(victim, victim_data);
    }
}
