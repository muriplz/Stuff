package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class onInvInteract implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if(p.hasPermission("group.staff") && !p.getName().equals("Satoryc")) return;

        String itemStack = event.getCurrentItem().toString();

        if(itemStack == null) return;

        // Cancel inventory clicks on the inventory opened by /invsee
        if(itemStack.contains("WORLDSHAPER")) {
            Utils.broadcast("&6" +p.getName() + " &fhas interacted with a WORLDSHAPER and got banned from the server");
            Utils.transferAllClaimsToMe(p);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " worldshaper are not allowed on this server");
        }
        if(itemStack.contains("CREATE_CREATIVE_CRATE")) {
            Utils.broadcast("&6" +p.getName() + " &fhas interacted with a creative crate and got banned from the server");
            Utils.transferAllClaimsToMe(p);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " Creative Crates are not allowed on this server");

        } else if(itemStack.contains("CREATIVE")) {
            Utils.broadcast("&6" +p.getName() + " &fhas interacted with a " + event.getCurrentItem().toString() + " and got banned from the server");
            Utils.transferAllClaimsToMe(p);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + event.getCurrentItem().toString() + " are not allowed on this server");
        }
    }

}
