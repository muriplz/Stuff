package com.kryeit.Listener;

import com.kryeit.Stuff;
import com.kryeit.storage.Properties.DiskMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.Collection;
import java.util.List;

public class onMutedMessage implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if(Stuff.getInstance().hardMuted.containsKey(DiskMap.uuidToByteArray(player.getUniqueId()))) {
            player.sendMessage("You have been hard muted.");
            event.setCancelled(true);
            return;
        }

        if(Stuff.getInstance().softMuted.containsKey(DiskMap.uuidToByteArray(player.getUniqueId()))) {
            player.sendMessage("You have been soft muted.");
            event.setCancelled(true);
        }
    }
}
