package com.kryeit.Listener;

import com.kryeit.Stuff;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class onPhantomSpawn implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM) {
            World world = event.getEntity().getWorld();
            int phantomCount = Stuff.getInstance().phantomCountMap.getOrDefault(world, 0);

            if (phantomCount >= Stuff.getInstance().maxPhantomCount) {
                event.setCancelled(true);
                return;
            }

            Location spawnLocation = event.getLocation();
            Block blockBelow = world.getBlockAt(spawnLocation.getBlockX(), spawnLocation.getBlockY() - 1, spawnLocation.getBlockZ());
            if (blockBelow.getType() != Material.AIR) {
                Stuff.getInstance().phantomCountMap.put(world, phantomCount + 1);
            }
        }
    }

}
