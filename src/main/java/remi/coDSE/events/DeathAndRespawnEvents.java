package remi.coDSE.events;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.MaxMoneyException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.data.PlayerData;
import remi.coDSE.utiliy.PlayerUtil;
import remi.coDSE.utiliy.SpawnUtil;
import remi.coDSE.utiliy.KillUtil;

// this class handles events related to dying and respawning

public class DeathAndRespawnEvents implements Listener {
    @EventHandler
    public void OnRespawn(@NotNull PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        SpawnUtil.Respawn(player);

    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer != null && killer != victim) { // victim can have a brain aneurysm, dont want that to cause a crash
            PlayerData killer_data = PlayerUtil.getPlayerData(killer);
            killer_data.incrementKills();
            PlayerUtil.setPlayerData(killer, killer_data);
            KillUtil.HandlePointsAndShit(killer, killer_data.getKills());
        }
        PlayerData victim_data = PlayerUtil.getPlayerData(victim);
        victim_data.setKills(0);

    }
}
