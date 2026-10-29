package remi.coDSE.utiliy;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class GeneralUtil {
    public static boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str);
        }
        catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    public static Component deserialize(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

    public static void messageShortcut(CommandSender recipient, String message) {
        recipient.sendMessage(deserialize(message));
    }
}
