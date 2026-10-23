package remi.coDSE.utiliy;

public class GeneralUtil {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        }
        catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
