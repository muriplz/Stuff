package com.kryeit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String color (String msg) {
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static String getTimeBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );

        StringBuilder time = new StringBuilder();
        if( years != 0 ) {
            time.append(years).append(" years ");
        }
        if( months != 0 ) {
            time.append(months).append(" months ");
        }
        if( days != 0 ) {
            time.append(days).append(" days ");
        }
        if( years != 0 && months != 0 && days != 0) {
            return "less than a day";
        }
        return time.toString();

    }

    public static boolean isOffline(String name) {
        return offlineNames().contains(name);
    }

    public static ArrayList<String> offlineNames() {
        ArrayList<String> list = new ArrayList<>();
        for(OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()){
            list.add(p.getName());
        }
        return list;
    }

    public static String getTime(int secondsx) {
        int days = (int) TimeUnit.SECONDS.toDays(secondsx);
        int hours = (int) (TimeUnit.SECONDS.toHours(secondsx) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.SECONDS.toMinutes(secondsx) - TimeUnit.HOURS.toMinutes(hours)
                - TimeUnit.DAYS.toMinutes(days));
        int seconds = (int) (TimeUnit.SECONDS.toSeconds(secondsx) - TimeUnit.MINUTES.toSeconds(minutes)
                - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.DAYS.toSeconds(days));

        if (days != 0) {
            return days + " &6days &f" + hours + " &6hours &f" + minutes + " &6minutes &f" + seconds + " &6seconds&f";
        } else {
            if (hours != 0) {
                return hours + " &6hours &f" + minutes + " &6minutes &f" + seconds + " &6seconds&f";
            } else {
                if (minutes != 0) {
                    return minutes + " &6minutes &f" + seconds + " &6seconds&f";
                } else {
                    return seconds + " &6seconds&f";
                }
            }

        }
    }
}
