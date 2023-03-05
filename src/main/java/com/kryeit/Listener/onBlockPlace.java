package com.kryeit.Listener;

import com.kryeit.Stuff;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onPlace (BlockPlaceEvent e){

        String b = e.getBlock().getType().toString();
        Player pl = e.getPlayer();

        if(b.equals("HOPPER") || b.equals("CREATE_ANDESITE_BELT_FUNNEL") || b.equals("CREATE_ANDESITE_FUNNEL") || b.equals("CREATE_BRASS_BELT_FUNNEL") || b.equals("CREATE_BRASS_FUNNEL") || b.equals("CREATE_SMART_CHUTE") || b.equals("CREATE_CHUTE")) {
            String ba = e.getBlockAgainst().getType().toString();
            if(ba.equals("CREATE_ITEM_VAULT") || ba.contains("BACKPACK") || ba.equals("CREATE_BELT")) {
                if(!Stuff.getInstance().warned.contains(pl.getUniqueId())) {
                    pl.sendMessage(Utils.color("&aFunnels and Chutes extracting from vaults and backpacks cause lag issues. It's recommended to switch to other alternatives.\nNote: If you power the brass funnel/chute with redstone it will be lag free."));
                    Stuff.getInstance().warned.add(pl.getUniqueId());
                }
            }
        }

        if(!target().contains(b)) return;

        String material = "";

        for (String s : target()) {
            if(s.equals(b)) {
                material = s;
                break;
            }
        }

        if (material.equals("")) {
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (p.getGameMode().equals(GameMode.SPECTATOR)) continue;

            if (p.equals(pl)) continue;

            if (!p.getWorld().equals(pl.getWorld())) continue;

            if (p.getLocation().distance(e.getBlock().getLocation()) < 5 && p.getWorld().equals(pl.getWorld())) {
                pl.sendMessage(Utils.color("&cYou cant place &6minecraft:"+ material.toLowerCase()+"&c near another player"));
                e.setCancelled(true);
            }

        }
    }

    public List<String> target () {
        List<String> target = new ArrayList<>();
        target.add("FIRE");
        target.add("MAGMA_BLOCK");
        target.add("OBSIDIAN");
        return target;
    }
}
