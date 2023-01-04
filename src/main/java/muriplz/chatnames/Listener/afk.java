package muriplz.chatnames.Listener;

import net.lapismc.afkplus.api.AFKStartEvent;
import net.lapismc.afkplus.api.AFKStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class afk implements Listener {

    @EventHandler
    public void onAfk(AFKStartEvent e) {
        Player p = Bukkit.getPlayer(e.getPlayer().getUUID()) ;

        if(p.getGameMode().equals(GameMode.SPECTATOR)){
            return;
        }
        p.setPlayerListName(ChatColor.GRAY + p.getName());
    }
    @EventHandler
    public void onAfkOut(AFKStopEvent e) {
        Player p = Bukkit.getPlayer(e.getPlayer().getUUID()) ;
        if(p.getGameMode().equals(GameMode.SPECTATOR)){
            return;
        }
        p.setPlayerListName(ChatColor.WHITE + p.getName());
    }
}
