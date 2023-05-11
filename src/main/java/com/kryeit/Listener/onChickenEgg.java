package com.kryeit.Listener;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class onChickenEgg implements Listener {

    @EventHandler
    public void onEggDrop(EntityDropItemEvent event) {
        if (event.getEntity() instanceof Chicken && event.getItemDrop().getItemStack().getType().name().equalsIgnoreCase("EGG")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        Claim claim = GriefDefender.getCore().getClaimAt(event.getEgg().getLocation());

        assert claim != null;
        if (claim.isWilderness()) return;

        event.setHatching(false); // Cancel the random hatching

        Random random = new Random();
        boolean shouldSpawnChicken = random.nextBoolean(); // 50% chance of being true

        if (shouldSpawnChicken) {
            event.setNumHatches((byte) 1); // Set the number of chickens to spawn to 1
            Chicken chicken = event.getPlayer().getWorld().spawn(event.getEgg().getLocation(), Chicken.class);
            chicken.setBaby();
        }

    }

    @EventHandler
    public void onChickenDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Chicken && !Objects.requireNonNull(GriefDefender.getCore().getClaimAt(event.getEntity().getLocation())).isWilderness() ) {
            int numEggs = 0;
            double rand = Math.random();
            if (rand < 0.40) {
                numEggs = 1;
            } else if (rand < 0.70) {
                numEggs = 2;
            } else if (rand < 0.80) {
                numEggs = 3;
            } else if (rand < 0.90) {
                numEggs = 4;
            } else {
                numEggs = 5;
            }
            ItemStack eggStack = new ItemStack(org.bukkit.Material.EGG, numEggs);
            event.getDrops().add(eggStack);
        }
    }

    @EventHandler
    public void onDispenserEggThrow(BlockDispenseEvent event) {
        Claim claim = GriefDefender.getCore().getClaimAt(event.getBlock().getLocation());

        assert claim != null;
        if(claim.isWilderness()) return;
        if (event.getItem().getType().equals(org.bukkit.Material.EGG)) {
            event.setCancelled(true); // Cancel the egg throwing
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation().add(0.5, 1.2, 0.5), EntityType.CHICKEN); // Spawn a chicken at the dispenser's location
        }
    }
}
