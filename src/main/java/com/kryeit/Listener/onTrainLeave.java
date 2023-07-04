package com.kryeit.Listener;

import com.kryeit.Stuff;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

public class onTrainLeave implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void trainLeave(EntityDismountEvent e) {
        if(!e.getDismounted().toString().contains("CARRIAGE")) return;
        if(e.getEntity() instanceof Player p) {
            Location loc = p.getLocation();
            Bukkit.getScheduler().runTaskLater(Stuff.getInstance(), () -> {
                p.teleport(loc);
            }, 1);
        }
    }
}
