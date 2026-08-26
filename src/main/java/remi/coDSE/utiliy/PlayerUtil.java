package remi.coDSE.utiliy;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.checkerframework.checker.nullness.qual.NonNull;
import remi.coDSE.data.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// this class mostly contains random bullshit related to players

public class PlayerUtil {

    // PlayerData functions
    private static final Map<String, PlayerData> PlayerData = new HashMap<>();

    public static PlayerData getPlayerData(@NonNull Player player) {
        if(!PlayerData.containsKey(player.getUniqueId().toString())) {
            PlayerData data = new PlayerData();
            PlayerData.put(player.getUniqueId().toString(), data);
            return data;
        }
        return PlayerData.get(player.getUniqueId().toString());

    }

    public static void setPlayerData(Player player, PlayerData data) {
        if(data == null) PlayerData.remove(player.getUniqueId().toString());
        else PlayerData.put(player.getUniqueId().toString(), data);
    }



    // perk shenanigans

    // defining premade potion effects
    private static final PotionEffect base_regen = new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 1, false, false);
    private static final PotionEffect fast_regen = new PotionEffect(PotionEffectType.REGENERATION, PotionEffect.INFINITE_DURATION, 3, false, false);
    private static final PotionEffect slow_fall = new PotionEffect(PotionEffectType.SLOW_FALLING, PotionEffect.INFINITE_DURATION, 0, false, false);
    private static final PotionEffect saturation = new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 0, false, false);

    public static void ApplyPerk(int perk, @NonNull Player player) {
        switch (perk) {
            case 1: //staminup -> speed+
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.15);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(60);
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(base_regen);
                player.addPotionEffect(saturation);
                break;

            case 2: // quick revive -> regen+
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.102);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(60);
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(fast_regen);
                player.addPotionEffect(saturation);
                break;

            case 3: // juggernog -> health+
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.102);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(80);
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(base_regen);
                player.addPotionEffect(saturation);
                break;

            case 4: // feather's curse -> speed++, slow fall, health-
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.175);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(40);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(base_regen);
                player.addPotionEffect(slow_fall);
                player.addPotionEffect(saturation);
                break;

            default: // no perk :3
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.102);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(60);
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(base_regen);
                player.addPotionEffect(saturation);
        }
    }
}
