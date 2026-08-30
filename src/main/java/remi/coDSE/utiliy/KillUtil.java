package remi.coDSE.utiliy;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.deecaad.weaponmechanics.WeaponMechanicsAPI;
import net.ess3.api.MaxMoneyException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import com.alonsoaliaga.alonsoleagues.api.AlonsoLeaguesAPI;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Random;

public class KillUtil {

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
        meta.displayName(Component.text("Reactive armor", TextColor.fromHexString("#ff55ff")));
        meta.addEnchant(Enchantment.THORNS, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("armor", 10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("t", 20, AttributeModifier.Operation.ADD_NUMBER));
        meta.setMaxDamage(15);
        reactive_armor.setItemMeta(meta);

        meta = (Damageable) t1helmet.getItemMeta();
        meta.displayName(Component.text("Tier I Helmet"));
        meta.setMaxDamage(50);
        t1helmet.setItemMeta(meta);

        meta = (Damageable) t1chestplate.getItemMeta();
        meta.displayName(Component.text("Tier I Chestplate"));
        meta.setMaxDamage(80);
        t1chestplate.setItemMeta(meta);

        meta = (Damageable) t2helmet.getItemMeta();
        meta.displayName(Component.text("Tier II Helmet"));
        meta.setMaxDamage(120);
        t2helmet.setItemMeta(meta);

        meta = (Damageable) t2chestplate.getItemMeta();
        meta.displayName(Component.text("Tier II Chestplate"));
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
                        player.sendMessage(Component.text().content("+T1 Helmet").color(TextColor.fromHexString("#ffffff")).build());
                        break;

                    case 1:
                        inventory.addItem(t1chestplate);
                        player.sendMessage(Component.text().content("+T1 Chestplate").color(TextColor.fromHexString("#ffffff")).build());
                        break;

                    case 2:
                        inventory.addItem(t2helmet);
                        player.sendMessage(Component.text().content("+T2 Helmet").color(TextColor.fromHexString("#00ffff")).build());
                        break;

                    case 3:
                        inventory.addItem(t2chestplate);
                        player.sendMessage(Component.text().content("+T2 Chestplate").color(TextColor.fromHexString("#00ffff")).build());
                        break;

                    default:
                        break;

                }
            } else {
                inventory.addItem(reactive_armor);
                player.sendMessage(Component.text().content("+Reactive Armor").color(TextColor.fromHexString("#ff55ff")).build());
            }
        }
    }

    private static void GiveUserItem(@NotNull Player player) {
        // give user items
        switch (random.nextInt(7)) {
            case 0 -> {
                WeaponMechanicsAPI.giveWeapon("STIM", player);
                player.sendMessage(Component.text().content("+STIM").color(TextColor.fromHexString("#009aff")).build());
            }
            case 1 -> {
                WeaponMechanicsAPI.giveWeapon("LightArmorPlate", player);
                player.sendMessage(Component.text().content("+Light armor Plate").color(TextColor.fromHexString("#00ff00")).build());
            }
            case 2 -> {
                WeaponMechanicsAPI.giveWeapon("MediumArmorPlate", player);
                player.sendMessage(Component.text().content("+Medium Armor Plate").color(TextColor.fromHexString("#ffff00")).build());
            }
            case 3 -> {
                WeaponMechanicsAPI.giveWeapon("HeavyArmorPlate", player);
                player.sendMessage(Component.text().content("+Heavy Armor Plate").color(TextColor.fromHexString("#ff0000")).build());
            }
            case 4 -> {
                WeaponMechanicsAPI.giveWeapon("GRENADE", player);
                player.sendMessage(Component.text().content("+Grenade").color(TextColor.fromHexString("#308D30")).build());
            }
            case 5 -> {
                WeaponMechanicsAPI.giveWeapon("SEMTEX", player);
                player.sendMessage(Component.text().content("+Semtex").color(TextColor.fromHexString("#ffffff")).build());
            }
            case 6 -> {
                WeaponMechanicsAPI.giveWeapon("IMPACTGRENADE", player);
                player.sendMessage(Component.text().content("+Impact ").color(TextColor.fromHexString("#7A7A7A")).append(Component.text("Grenade", TextColor.fromHexString("#308D30"))).build());
            }
            default -> player.sendMessage(Component.text("+ nothing lmao"));
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
        player.sendMessage(Component.text().content(String.format("+%s points / +%s hungarian pengő", points, money)).color(TextColor.fromHexString("#ffaa00")).build());
    }

    private static void Fish(Player player) {
        // 1/69420 chance of getting the fish
        if (random.nextInt(69420) == 0) {
            WeaponMechanicsAPI.giveWeapon("ADMINjustno", player);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Component.text(player + " JUST OBTAINED THE FISH, RUN WHILST YOU STILL CAN", TextColor.fromHexString("#FF0000")).decoration(TextDecoration.BOLD, true));
            }
        }
    }

    private static void GiveKillstreakItem(@NotNull Player player, int kills) {
        if (kills >= 1) {
            if (kills % 5 == 0) {
                player.playSound(player.getLocation(), "minecraft:misc.tf_domination", 10, 1);
                for (Player p : Bukkit.getOnlinePlayers()) {
                   p.sendMessage(MiniMessage.miniMessage().deserialize(String.format("<color:#55ff55>%s</color> is on a killstreak of <color:#ff0000>%s</color>!", player.getName(), kills)));
                }
            }
            if (kills % 7 == 0) {
                WeaponMechanicsAPI.giveWeapon("Panzerfaust30", player);
                player.sendMessage(Component.text("+Panzerfaust 30 ", TextColor.fromHexString("#E4C04B")));
            }
            if (kills % 15 == 0) {
                WeaponMechanicsAPI.giveWeapon("mgb",  player);
                player.sendMessage(Component.text("+MGB ", TextColor.fromHexString("#383327")));
            }
            if (kills % 30 == 0) {
                WeaponMechanicsAPI.giveWeapon("FULLAUTOMGBLAUNCHER",  player);
                player.sendMessage(Component.text("+Full Auto MGB Launcher ", TextColor.fromHexString("#ff0000")));
            }
        }
    }
}
