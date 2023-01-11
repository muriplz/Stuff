package com.kryeit.commands;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class LastOnline implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }
        Player other = Bukkit.getPlayer(args[0]);
        if(other == null){
            player.sendMessage("Player not found");
            return false;
        }

        for(Claim claim : GriefDefender.getCore().getUser(other.getUniqueId()).getPlayerData().getClaims()) {
            LocalDateTime date = LocalDateTime.now();
            LocalDateTime lastonline = LocalDateTime.ofInstant(claim.getData().getDateLastActive(), ZoneOffset.UTC);
            String[] chars = claim.getData().getDateLastActive().toString().split("");
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String ch : chars) {

                sb.append(ch);

                if(i == 10) break;

                i++;
            }

            String howLong = Utils.getTimeBetween(lastonline,date);

            player.sendMessage(Utils.color("The last time &6" + other.getName() + "&f has been online is &6" + sb +
                    "&f, and has been offline for &6" + howLong));
            break;
        }
        return true;
    }
}
