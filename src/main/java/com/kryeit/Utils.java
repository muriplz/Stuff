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
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        int years = (int) tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        int months = (int) tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        int days = (int) tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusMonths(days);

        int hours = (int) tempDateTime.until(toDateTime, ChronoUnit.HOURS);

        StringBuilder time = new StringBuilder();
        if(years != 0) {
            if(years == 1) {
                time.append(years).append(" year ");
            }else{
                time.append(years).append(" years ");
            }
        }
        if(months != 0) {
            if(months == 1) {
                time.append(years).append(" month ");
            }else{
                time.append(years).append(" months ");
            }
        }
        if(days != 0) {
            if(days == 1) {
                time.append(years).append(" day ");
            }else{
                time.append(years).append(" days ");
            }
        }
        if(hours != 0) {
            if(hours == 1) {
                time.append(years).append(" hour ");
            }else{
                time.append(years).append(" hours ");
            }
        }
        if(years == 0 && months == 0 && days == 0 && hours == 0) {
            return "less than an hour";
        }
        return time.toString();

    }

    public static boolean isOffline(String name) {
        return offlineNames().contains(name);
    }

    public static ArrayList<String> offlineNames() {
        ArrayList<String> list = new ArrayList<>();
        for(OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
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
