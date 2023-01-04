package com.kryeit.Listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onPlace (BlockPlaceEvent e){

        if(!target().contains(e.getBlock().getType().toString())){
            return;
        }

        String mat="";

        for(String s : target()){
            if(s.equals(e.getBlock().getType().toString())){
                mat=s;
                break;
            }
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            if(!p.equals(e.getPlayer())&&p.getWorld().equals(e.getPlayer().getWorld())){
                if(p.getLocation().distance(e.getBlock().getLocation()) < 5){
                    e.getPlayer().sendMessage(Utils.color("&cYou cant place &6minecraft:"+ mat.toLowerCase()+"&c near another player"));
                    e.setCancelled(true);
                }
            }

        }
    }

    public List<String> target(){
        List<String> target = new ArrayList<>();
        target.add("FIRE");
        target.add("MAGMA_BLOCK");
        target.add("OBSIDIAN");
        return target;
    }
}
