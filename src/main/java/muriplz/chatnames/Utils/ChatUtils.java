package muriplz.chatnames.Utils;

import org.bukkit.ChatColor;

public class ChatUtils {
    public static String color (String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }
}
