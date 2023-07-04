package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onEnzoMove implements Listener {

    @EventHandler
    public void enzoMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!player.getName().equals("Enzoquest10")) return;

        for (Cat cat : player.getWorld().getEntitiesByClass(Cat.class)) {
            if (player.getLocation().distance(cat.getLocation()) <= 3) {
                // Kill the player
                player.setHealth(0);
                Utils.broadcast("Enzoquest10 has been heard snoozing from Piltrafilla");
                break;
            }
        }
    }
}
