package com.kryeit.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GetInventory implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /getinventory <OfflinePlayer>");
            return true;
        }

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
        if (!targetPlayer.hasPlayedBefore()) {
            sender.sendMessage("That player has never played on this server before.");
            return true;
        }

        Inventory targetInventory = targetPlayer.getPlayer().getInventory();
        Inventory playerInventory = player.getInventory();

        if (targetInventory.firstEmpty() == -1) {
            // Target player's inventory is full, so transfer items to the command sender's inventory
            for (ItemStack item : targetInventory.getContents()) {
                if (item != null) {
                    playerInventory.addItem(item);
                }
            }
            targetInventory.clear();
            player.sendMessage("The target player's inventory was full, so their items were transferred to your inventory.");
        } else {
            // Target player's inventory has empty slots, so transfer items to those slots
            for (ItemStack item : targetInventory.getContents()) {
                if (item != null) {
                    targetInventory.removeItem(item);
                    targetInventory.addItem(item);
                }
            }
            player.sendMessage("The target player's items were added to their inventory.");
        }

        return true;
    }
}
