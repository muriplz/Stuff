package com.kryeit.Listener;

import com.kryeit.Stuff;
import com.kryeit.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.kryeit.Utils.isPlayerInGroup;

public class onMessageSent implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player p = e.getPlayer();
        TextComponent t = getMessage(p);
        TextComponent t2 = new TextComponent(message);

        t2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                "/m " + p.getName() + " "));

        if( message.contains("trapped") || message.contains("stuck") || message.contains("get out")){
            p.sendMessage(Utils.color("&bIf you can't get out of somewhere, use /trapped"));
        }

        e.setCancelled(true);

        Stuff.getInstance().getServer().spigot().broadcast(t,t2);

    }

    public static TextComponent getMessage(Player p){

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
            color = "&d";
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

        String temp = Utils.color(color + name + "&f: ");
        String hover = Utils.color(name+"'s rank is "
                + color + group + "&7\n\nClick to whisper");

        TextComponent t = new TextComponent(temp);

        t.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));

        t.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                "/m " + name + " "));

        return t;

    }
}
