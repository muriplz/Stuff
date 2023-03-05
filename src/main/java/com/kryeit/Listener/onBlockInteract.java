package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class onBlockInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getItem() == null) return;

        String material = "";
        boolean condition = false;

        for (String mat : target()) {
            if (!Objects.requireNonNull(e.getItem()).toString().contains(mat)) continue;

            material = mat;
            condition = true;
            break;
        }

        if (!condition) return;

        double distance = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getGameMode().equals(GameMode.SPECTATOR)) continue;

            if (p.equals(e.getPlayer())) continue;

            if (!p.getWorld().equals(e.getPlayer().getWorld())) continue;

            distance = p.getLocation().distance(e.getPlayer().getLocation());
            if (distance < 10) {
                e.getPlayer().sendMessage(Utils.color("&cYou cant interact with &6minecraft:"
                        + material.toLowerCase() + "&c near another player"));
                e.setCancelled(true);
                return;
            }
        }
    }

    public List<String> target () {
        List<String> target = new ArrayList<>();
        target.add("LAVA_BUCKET");
        target.add("END_CRYSTAL");
        return target;
    }
}
