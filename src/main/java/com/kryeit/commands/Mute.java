package com.kryeit.commands;

import com.kryeit.Stuff;
import com.kryeit.storage.Properties.DiskMap;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class Mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length != 3) {
            sender.sendMessage("/mute <soft/hard> <Player> <days>");
            return false;
        }

        String muteType = args[0];
        Player player = Bukkit.getPlayer(args[1]);

        UUID uuid = null;

        int days = Integer.parseInt(args[2]);

        if(player == null) {
            for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                if(!Objects.equals(offlinePlayer.getName(), args[1])) continue;
                uuid = offlinePlayer.getUniqueId();
            }
        } else {
            uuid = player.getUniqueId();
        }

        if(uuid == null) {
            sender.sendMessage("Player not found");
            return false;
        }

        if(Objects.equals(muteType, "soft")) {
            Stuff.getInstance().softMuted.put(DiskMap.uuidToByteArray(uuid),DiskMap.intToByteArray(days));
            sender.sendMessage("Player " + args[1] + " has been soft muted for " + days + " days");
            return true;
        } else if(Objects.equals(muteType,"hard")) {
            Stuff.getInstance().hardMuted.put(DiskMap.uuidToByteArray(uuid),DiskMap.intToByteArray(days));
            sender.sendMessage("Player " + args[1] + " has been hard muted for " + days + " days");
            return true;
        }

        sender.sendMessage("/mute <soft/hard> <Player> <days>");
        return false;
    }
}
