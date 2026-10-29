package remi.coDSE.utiliy;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.deecaad.weaponmechanics.WeaponMechanicsAPI;
import net.ess3.api.MaxMoneyException;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import com.alonsoaliaga.alonsoleagues.api.AlonsoLeaguesAPI;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;
import remi.coDSE.core.CoDSE;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;

public class KillUtil {
    static final Title.Times times = Title.Times.times(Duration.ofMillis(250), Duration.ofMillis(2500), Duration.ofMillis(250));

    public static void HandlePointsAndShit(Player player, int kills) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {

        GiveKillstreakItem(player, kills);
        GiveArmor(player);
        GiveUserItem(player);
        GiveMoneyandPoints(player, kills);
        Fish(player);

    }

    final static Random random = new Random();

    // setting up armor
    static final ItemStack reactive_armor = new ItemStack(Material.GOLDEN_CHESTPLATE);
    static final ItemStack t1helmet = new ItemStack(Material.IRON_HELMET);
    static final ItemStack t1chestplate = new ItemStack(Material.IRON_CHESTPLATE);
    static final ItemStack t2helmet = new ItemStack(Material.DIAMOND_HELMET);
    static final ItemStack t2chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);

    static {
        Damageable meta = (Damageable) reactive_armor.getItemMeta();
        meta.displayName(GeneralUtil.deserialize("<color:#FF55FF>Reactive Armor</color>"));
        meta.addEnchant(Enchantment.THORNS, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addAttributeModifier(Attribute.ARMOR, new AttributeModifier(new NamespacedKey("remi", "armor"), 10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, new AttributeModifier(new NamespacedKey("remi", "toughness"), 20, AttributeModifier.Operation.ADD_NUMBER));
        meta.setMaxDamage(15);
        reactive_armor.setItemMeta(meta);

        meta = (Damageable) t1helmet.getItemMeta();
        meta.displayName(GeneralUtil.deserialize("<color:#FFFFFF>T1 Helmet</color>"));
        meta.setMaxDamage(50);
        t1helmet.setItemMeta(meta);

        meta = (Damageable) t1chestplate.getItemMeta();
        meta.displayName(GeneralUtil.deserialize("<color:#FFFFFF>T1 Chestplate</color>"));
        meta.setMaxDamage(80);
        t1chestplate.setItemMeta(meta);

        meta = (Damageable) t2helmet.getItemMeta();
        meta.displayName(GeneralUtil.deserialize("<color:#00FFFF>T2 Helmet</color>"));
        meta.setMaxDamage(120);
        t2helmet.setItemMeta(meta);

        meta = (Damageable) t2chestplate.getItemMeta();
        meta.displayName(GeneralUtil.deserialize("<color:#00FFFF>T2 Chestplate</color>"));
        meta.setMaxDamage(175);
        t2chestplate.setItemMeta(meta);
    }

    private static void GiveArmor(@NotNull Player player) {
        PlayerInventory inventory = player.getInventory();

        // 1/8 chance to get armor
        // then 1/50 to get reactive armor
        // or u get a t1/t2 helmet/chestplate

        if (random.nextInt(8) == 0) {
            if (random.nextInt(50) != 0) {
                switch (random.nextInt(4)) {
                    case 0:
                        inventory.addItem(t1helmet);
                        GeneralUtil.sendMessage(player, "<color:#FFFFFF>+T1 Helmet</color>");
                        break;

                    case 1:
                        inventory.addItem(t1chestplate);
                        GeneralUtil.sendMessage(player, "<color:#FFFFFF>+T1 Chestplate</color>");
                        break;

                    case 2:
                        inventory.addItem(t2helmet);
                        GeneralUtil.sendMessage(player, "<color:#00FFFF>+T2 Helmet</color>");
                        break;

                    case 3:
                        inventory.addItem(t2chestplate);
                        GeneralUtil.sendMessage(player, "<color:#00FFFF>+T2 Chestplate</color>");
                        break;

                    default:
                        break;

                }
            } else {
                inventory.addItem(reactive_armor);
                GeneralUtil.sendMessage(player, "<color:#FF55FF>+Reactive Armor</color>");
            }
        }
    }

    private static void GiveUserItem(@NotNull Player player) {
        // give user items
        switch (random.nextInt(7)) {
            case 0 -> {
                WeaponMechanicsAPI.giveWeapon("STIM", player);
                GeneralUtil.sendMessage(player, "<color:#009AFF>+STIM</color>");
            }
            case 1 -> {
                WeaponMechanicsAPI.giveWeapon("LightArmorPlate", player);
                GeneralUtil.sendMessage(player,"<color:#00FF00>+Light armor Plate</color>");
            }
            case 2 -> {
                WeaponMechanicsAPI.giveWeapon("MediumArmorPlate", player);
                GeneralUtil.sendMessage(player, "<color#FFFF00>+Medium Armor Plate</color>");
            }
            case 3 -> {
                WeaponMechanicsAPI.giveWeapon("HeavyArmorPlate", player);
                GeneralUtil.sendMessage(player,"<color:#FF0000>+Heavy Armor Plate</color>");
            }
            case 4 -> {
                WeaponMechanicsAPI.giveWeapon("GRENADE", player);
                GeneralUtil.sendMessage(player,"<color:#308D30>+Grenade");
            }
            case 5 -> {
                WeaponMechanicsAPI.giveWeapon("SEMTEX", player);
                GeneralUtil.sendMessage(player, "<color:#FFFFFF>+Semtex</color>");
            }
            case 6 -> {
                WeaponMechanicsAPI.giveWeapon("IMPACTGRENADE", player);
               GeneralUtil.sendMessage(player, "<color:#7A7A7A>+Impact</color> <color#308D30>Grenade</color>");
            }
            default -> GeneralUtil.sendMessage(player, "<color:#FFFFFF>+ nothing lmao</color>");
        }
    }

    private static void GiveMoneyandPoints(@NotNull Player player, int kills) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
        // give points and money
        AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 50);
        Economy.add(player.getUniqueId(), BigDecimal.valueOf(100));
        int money = 100;
        int points = 50;

        if (kills >= 5) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 10);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(40));
            points += 10;
            money += 40;
        }

        if (kills >= 10) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 20);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(80));
            points += 20;
            money += 80;
        }

        if (kills >= 20) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 40);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(160));
            points += 40;
            money += 160;
        }

        if (kills >= 30) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 60);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(240));
            points += 60;
            money += 240;
        }

        if (kills >= 50) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 100);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(400));
            points += 100;
            money += 400;
        }

        if (kills >= 75) {
            AlonsoLeaguesAPI.addPoints(player.getUniqueId(), 150);
            Economy.add(player.getUniqueId(), BigDecimal.valueOf(600));
            points += 150;
            money += 600;
        }
        GeneralUtil.sendMessage(player,"<color:#FFAA00>+%s points / +%s hungarian pengő</color>".formatted(points, money));
    }

    private static void Fish(Player player) {
        // 1/69420 chance of getting the fish
        if (random.nextInt(69420) == 0) {
            WeaponMechanicsAPI.giveWeapon("ADMINjustno", player);
            Title title = Title.title(GeneralUtil.deserialize("<color:#FF0000><b>%s HAS OBTAINED THE FISH</b></color>".formatted(player.getName())), GeneralUtil.deserialize("<color:#FF0000><b>RUN WHILST YOU STILL CAN</b></color>"), times);
            CoDSE.server.showTitle(title);
        }
    }

    private static void GiveKillstreakItem(@NotNull Player player, int kills) {
        if (kills >= 1) {
            if (kills % 5 == 0) {
                player.playSound(player.getLocation(), "minecraft:misc.tf_domination", 10, 1);
                for (Player p : Bukkit.getOnlinePlayers()) {
                   GeneralUtil.sendMessage(p, String.format("<color:#55ff55>%s</color> is on a killstreak of <color:#ff0000>%s</color>!", player.getName(), kills));
                }
            }
            if (kills % 7 == 0) {
                WeaponMechanicsAPI.giveWeapon("Panzerfaust30", player);
                GeneralUtil.sendMessage(player,"<color:#E4C04B>+Panzerfaust 30 </color>");
            }
            if (kills % 15 == 0) {
                WeaponMechanicsAPI.giveWeapon("MGB",  player);
                GeneralUtil.sendMessage(player,"<color:#383327>+MGB </color>");
            }
            if (kills % 30 == 0) {
                WeaponMechanicsAPI.giveWeapon("FULLAUTOMGBLAUNCHER",  player);
                GeneralUtil.sendMessage(player, "<color:#FF0000>+Full Auto MGB Launcher </color>");
            }
        }
    }
}
