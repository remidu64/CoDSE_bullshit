package remi.coDSE.utiliy;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.kyori.adventure.text.Component;
import remi.coDSE.core.CoDSE;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PhasetwoUtil {
    static Random random = new Random();

    public static void Phase2(Player player) {
        PlayerUtil.ApplyPerk(PlayerUtil.getPlayerData(player).getPerk(), player);
        RememberSon(player);
    }

    private static void RememberSon(Player player) {
        // 0s -> blindness for 46s, stopped for 46s, immunity for 46s
        // 22s -> remember son dying is gay title
        // 48s -> adr for 30s
        player.addPotionEffects(List.of(blindness_48s, stop_48s, immunity_48s));

        final Title.Times times = Title.Times.times(Duration.ofMillis(500), Duration.ofMillis(2000), Duration.ofMillis(350));
        final Title remember_son = Title.title(GeneralUtil.deserialize("<color:#FFFFFF>Remember son</color>"), Component.empty(), times);
        final Title dying_is_gay = Title.title(GeneralUtil.deserialize("<color:#FFFFFF>Dying is gay</color>"), Component.empty(), times);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.playSound(player.getLocation(), "meme.encounter", 1.0f, 1.0f);
        }, 40);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.showTitle(remember_son);
        }, 430);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.showTitle(dying_is_gay);
        }, 515);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.addPotionEffects(List.of(adr_speed, adr_resistance));
        }, 960);

    }

    private static void NeverBackDownNeverWhat(Player player) { // TODO: actually work on ts, only keeping AEE meme for now
        player.addPotionEffects(List.of(blindness_8s, stop_8s, immunity_8s));
        final Title.Times times = Title.Times.times(Duration.ofMillis(3000), Duration.ofMillis(1000), Duration.ofMillis(2500));
        final Title title = Title.title(Component.text("Lets not give up just yet", NamedTextColor.WHITE), Component.empty(), times);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.showTitle(title);
        }, 40);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CoDSE.getInstance(), () -> {
            player.addPotionEffects(List.of(adr_speed, adr_resistance));
        }, 160);
    }

    private static final PotionEffect blindness_8s = new PotionEffect(PotionEffectType.BLINDNESS, 160, 0, false, false);
    private static final PotionEffect stop_8s = new PotionEffect(PotionEffectType.SLOWNESS, 160, 255, false, false);
    private static final PotionEffect immunity_8s = new PotionEffect(PotionEffectType.RESISTANCE, 160, 255, false, false);
    private static final PotionEffect blindness_48s = new PotionEffect(PotionEffectType.BLINDNESS, 960, 0, false, false);
    private static final PotionEffect stop_48s = new PotionEffect(PotionEffectType.SLOWNESS, 960, 255, false, false);
    private static final PotionEffect immunity_48s = new PotionEffect(PotionEffectType.RESISTANCE, 960, 255, false, false);

    private static final PotionEffect adr_speed = new PotionEffect(PotionEffectType.SPEED, 600, 1, false, false);
    private static final PotionEffect adr_resistance = new PotionEffect(PotionEffectType.RESISTANCE, 600, 0, false, false);
}