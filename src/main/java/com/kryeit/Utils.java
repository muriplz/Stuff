package com.kryeit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static String color (String msg){
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
