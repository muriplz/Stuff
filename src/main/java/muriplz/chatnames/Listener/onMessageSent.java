package muriplz.chatnames.Listener;

import muriplz.chatnames.Utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onMessageSent implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();
        String t = getMessage(p, message);
        if( message.contains("trapped") || message.contains("stuck") || message.contains("get out")){
            p.sendMessage(ChatUtils.color("&bIf you can't get out of somewhere, use /trapped"));
        }
        e.setFormat(t);
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static String getMessage(Player p, String message){
        String name = p.getName();
        String color;
        String group;
        if(isPlayerInGroup(p,"staff")){
            color = "&a";
            group = "Staff";
        }else if(isPlayerInGroup(p,"booster")){
            color = "&5";
            group = "Booster";
        }
        else if(isPlayerInGroup(p,"helper")){
            color = "&b";
            group = "Helper";
        }
        else if(isPlayerInGroup(p,"collaborator")) {
            color = "&6";
            group = "Collaborator";
        }else{
            color = "&7";
            group = "Default";
        }
        String temp = color+name+"&f: ";
        String hover = name+"'s rank is "+color+group+"&7\n\nClick to whisper";
        String t2 = "";
        t2= t2+temp;

        t2=ChatColor.translateAlternateColorCodes('&',t2);
        t2= t2+message;
        return t2;

    }
}
