package muriplz.chatnames.Listener;

import muriplz.chatnames.ChatNames;
import muriplz.chatnames.Utils.ChatUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onNewPlayerJoin (PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            for (Player player : Bukkit.getOnlinePlayers()){
                if(!player.getName().equals(p.getName())){
                    player.sendMessage(ChatUtils.color("&b"+p.getName()+" has joined for the first time! Welcome!"));
                }
            }
        }
    }
}
