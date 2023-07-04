package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onNewPlayerJoin (PlayerJoinEvent e){

        Player p = e.getPlayer();

        if(p.hasPermission("group.kryeitor")) {
            p.setPlayerListName(Utils.color("&6&l⚙ &r" + p.getName()));
        }

        if (p.hasPlayedBefore()) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(p.getName())) {
                p.sendMessage(Utils.color("&bWelcome to Kryeit! see /rules and /posthelp for additional information."));
                p.sendMessage(Utils.color("&bFor claiming guide see https://docs.griefdefender.com/ and command usage use /gd."));
                continue;
            }
            player.sendMessage(Utils.color("&b" + p.getName()
                    + " has joined Kryeit for the first time! Welcome!"));
        }
    }
}
