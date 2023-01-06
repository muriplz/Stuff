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

        if(b.equals("CREATE_BRASS_BELT_FUNNEL") || b.equals("CREATE_BRASS_FUNNEL")){
            String ba = e.getBlockAgainst().getType().toString();
            if(ba.equals("CREATE_ITEM_VAULT") || ba.contains("BACKPACK")){
                if(!Stuff.warned.contains(pl.getUniqueId())){
                    pl.sendMessage(Utils.color("&aFunnels placed on vaults and backpacks causes lag issues. It's recommended to switch to other alternatives.\nIf you power the funnel with redstone it will be lag free."));
                    Stuff.warned.add(pl.getUniqueId());
                }
            }
        }

        if(!target().contains(b)){
            return;
        }

        String mat="";

        for(String s : target()){
            if(s.equals(b)){
                mat=s;
                break;
            }
        }

        for(Player p : Bukkit.getOnlinePlayers()){

            if(p.getGameMode().equals(GameMode.SPECTATOR)) continue;

            if(!p.equals(pl) && p.getWorld().equals(pl.getWorld())){
                if(p.getLocation().distance(e.getBlock().getLocation()) < 5){
                    pl.sendMessage(Utils.color("&cYou cant place &6minecraft:"+ mat.toLowerCase()+"&c near another player"));
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
