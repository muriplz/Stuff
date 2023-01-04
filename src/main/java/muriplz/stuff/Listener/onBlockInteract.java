package muriplz.stuff.Listener;

import muriplz.chatnames.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class onBlockInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        if(e.getItem()==null){
            return;
        }
        String mat=" ";
        boolean condition=false;


        for(String s : target()){
            if(Objects.requireNonNull(e.getItem()).toString().contains(s)){
                mat=s;
                condition=true;
                break;
            }
        }

        if(!condition){
            return;
        }

        double distance = 0;
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!p.equals(e.getPlayer())&&p.getWorld().equals(e.getPlayer().getWorld())){

                distance = p.getLocation().distance(e.getPlayer().getLocation());
                if(distance < 8){
                    e.getPlayer().sendMessage(ChatUtils.color("&cYou cant interact with &6minecraft:"
                            + mat.toLowerCase() + "&c near another player"));
                    e.setCancelled(true);
                    return;
                }
            }

        }
    }

    public List<String> target(){
        List<String> target = new ArrayList<>();
        target.add("LAVA_BUCKET");
        target.add("END_CRYSTAL");
        return target;
    }
}
