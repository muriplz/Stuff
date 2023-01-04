package muriplz.stuff.Listener;

import muriplz.stuff.Utils.ChatUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class onMessageSent implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();
        TextComponent t = getMessage(p, message);

        if( message.contains("trapped") || message.contains("stuck") || message.contains("get out")){
            p.sendMessage(ChatUtils.color("&bIf you can't get out of somewhere, use /trapped"));
        }
        e.setCancelled(true);
        p.spigot().sendMessage(ChatMessageType.valueOf(t+message));

    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static TextComponent getMessage(Player p, String message){

        String name = p.getName();
        String color;
        String group;

        if(isPlayerInGroup(p,"staff")){
            color = "&a";
            group = "Staff";
        }
        else if(isPlayerInGroup(p,"helper")){
            color = "&b";
            group = "Helper";
        }
        else if(isPlayerInGroup(p,"booster")){
            color = "&5";
            group = "Booster";
        }
        else if(isPlayerInGroup(p,"collaborator")) {
            color = "&6";
            group = "Collaborator";
        }
        else{
            color = "&7";
            group = "Default";
        }

        String temp = color + name + "&f: ";
        String hover = name+"'s rank is "+color+group+"&7\n\nClick to whisper";

        TextComponent t = new TextComponent(temp);
        t.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
        return t;

    }
}
