package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class onItemPickUp implements Listener {

    @EventHandler
    public void onItemPick (PlayerPickupItemEvent e){
        String itemStack = e.getItem().getItemStack().toString();
        Player p = e.getPlayer();
        if(p.hasPermission("group.staff")) return;
        if (itemStack.contains("CREATIVE") || itemStack.contains("WORLDSHAPER") || itemStack.contains("SPAWN_EGG") ||
                itemStack.contains("BEDROCK") || itemStack.contains("COMMAND_BLOCK") || itemStack.contains("STRUCTURE_BLOCK")) {
            Utils.broadcast("&6" +p.getName() + " &fhas picked up " + itemStack + " and got banned from the server.");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " Creative items are not allowed on this server");
            Utils.transferAllClaimsToMe(p);
        }
    }
}
