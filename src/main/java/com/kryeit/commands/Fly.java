package com.kryeit.commands;

import com.kryeit.Stuff;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Fly implements CommandExecutor {

    List<UUID> flyEnabled = Stuff.getInstance().flyEnabled;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        if(flyEnabled.contains(player.getUniqueId())) {
            player.setAllowFlight(false);
            flyEnabled.remove(player.getUniqueId());
        }else{
            player.setAllowFlight(true);
            flyEnabled.add(player.getUniqueId());
        }

        return false;
    }
}
