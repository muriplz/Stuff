package com.kryeit.commands;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyLight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if(args.length == 0 || args.length > 2) {
            player.sendMessage("Usage: /buylight <light_intensity> <amount>");
            return false;
        }

        // Each light costs 250 Claim Blocks per light level.

        int lightLevel = Integer.parseInt(args[0]);
        int amount = 1;
        if(args.length == 2) {
            amount = Integer.parseInt(args[1]);
        }

        int cost = 250 * lightLevel * amount;

        User user = GriefDefender.getCore().getUser(player.getUniqueId());

        if(user == null) return false;
        if(user.getPlayerData().getRemainingClaimBlocks() < cost) {
            player.sendMessage("You don't have enough claim blocks to buy that many lights.");
            return false;
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " light{BlockStateTag:{level:" + lightLevel + "}} " + amount);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gd player adjustbonusblocks " + player.getName() + " " + - cost);

        player.sendMessage("You bought " + amount + " light(s) with a light level of " + lightLevel + " for " + cost + " claim blocks.");



        return false;
    }
}
