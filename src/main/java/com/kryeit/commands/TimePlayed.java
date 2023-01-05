package com.kryeit.commands;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimePlayed implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        Player p = (Player) sender;

        if(args.length != 1){
            p.sendMessage("You have to enter a player's name");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null){
            p.sendMessage("Player not found");
            return false;
        }

        int timeplayed = player.getStatistic(Statistic.TOTAL_WORLD_TIME);

        p.sendMessage(Utils.color("The player &6" + player.getName() + "&f has played for " + timeplayed));

        return true;

    }
}
