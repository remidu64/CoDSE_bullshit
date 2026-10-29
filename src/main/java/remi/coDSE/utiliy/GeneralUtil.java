package remi.coDSE.utiliy;


import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

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

    public static void sendMessage(Audience recipient, String message) { recipient.sendMessage(deserialize(message)); }
}
