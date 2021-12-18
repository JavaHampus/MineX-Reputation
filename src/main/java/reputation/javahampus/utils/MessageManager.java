package reputation.javahampus.utils;

import org.bukkit.ChatColor;
import reputation.javahampus.Reputation;

public class MessageManager {

    public static String convertMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
