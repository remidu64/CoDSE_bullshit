package remi.coDSE.utiliy;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import me.deecaad.weaponmechanics.WeaponMechanicsAPI;
import net.ess3.api.MaxMoneyException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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

        GiveArmor(player);
        String user = GiveUserItem(player);
        int[] MP =  GiveMoneyandPoints(player, kills);
        Fish(player);

        int money = MP[0];
        int points = MP[1];

        // inform player of what they obtained
        player.sendMessage(String.format(message, user, points, money, Economy.getMoneyExact(player.getUniqueId())));

    }

    final static Random random = new Random();
    final static String message = "+%s %n +%s points / +%s hungarian pengő %n bal: %s hungarian pengő";

    // setting up armor
    final static ItemStack reactive_armor = new ItemStack(Material.GOLDEN_CHESTPLATE);
    final static ItemStack t1helmet = new ItemStack(Material.IRON_HELMET);
    final static ItemStack t1chestplate = new ItemStack(Material.IRON_CHESTPLATE);
    final static ItemStack t2helmet = new ItemStack(Material.DIAMOND_HELMET);
    final static ItemStack t2chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);

    {
        Damageable meta = (Damageable) reactive_armor.getItemMeta();
        meta.displayName(Component.text("Reactive armor", TextColor.fromHexString("#aa00aa")));
        meta.addEnchant(Enchantment.THORNS, 3, false);
        meta.addEnchant(Enchantment.UNBREAKING, 3, false);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("armor", 20, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("t", 5, AttributeModifier.Operation.ADD_NUMBER));
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
                        break;

                    case 1:
                        inventory.addItem(t1chestplate);
                        break;

                    case 2:
                        inventory.addItem(t2helmet);
                        break;

                    case 3:
                        inventory.addItem(t2chestplate);
                        break;

                    default:
                        break;

                }
            } else {
                inventory.addItem(reactive_armor);
            }
        }
    }

    private static @NotNull String GiveUserItem(@NotNull Player player) {
        // give user items
        return switch (random.nextInt(7)) {
            case 0 -> {
                WeaponMechanicsAPI.giveWeapon("STIM", player);
                yield "STIM";
            }
            case 1 -> {
                WeaponMechanicsAPI.giveWeapon("LightArmorPlate", player);
                yield "Light Armor Plate";
            }
            case 2 -> {
                WeaponMechanicsAPI.giveWeapon("MediumArmorPlate", player);
                yield "Medium Armor Plate";
            }
            case 3 -> {
                WeaponMechanicsAPI.giveWeapon("HeavyArmorPlate", player);
                yield "Heavy Armor Plate";
            }
            case 4 -> {
                WeaponMechanicsAPI.giveWeapon("GRENADE", player);
                yield "Grenade";
            }
            case 5 -> {
                WeaponMechanicsAPI.giveWeapon("SEMTEX", player);
                yield "Semtex";
            }
            case 6 -> {
                WeaponMechanicsAPI.giveWeapon("IMPACT_GRENADE", player);
                yield "Impact Grenade";
            }
            default -> "nothing lmao";
        };
    }

    private static int @NotNull [] GiveMoneyandPoints(@NotNull Player player, int kills) throws MaxMoneyException, UserDoesNotExistException, NoLoanPermittedException {
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
        return new int[]{money, points};
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
}
