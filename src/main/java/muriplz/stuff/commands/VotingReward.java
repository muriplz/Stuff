package muriplz.chatnames.commands;

import net.lapismc.afkplus.playerdata.AFKPlusPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static muriplz.chatnames.ChatNames.api;

public class VotingReward implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String player = args[0];
        int reward = Integer.parseInt(args[1]);
        Player p = Bukkit.getPlayer(player);

        if(p == null) {
            return false;
        }
        AFKPlusPlayer guy;


        for(Player pla : Bukkit.getOnlinePlayers()) {
            if(!pla.getName().equals(player)) {
                guy = api.getPlayer(pla.getUniqueId());

                if(!guy.isAFK()){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gd player adjustbonusblocks " + pla.getName() + " " + reward);
                    String message = ChatColor.translateAlternateColorCodes('&', "&7Someone voted... +"+reward+" bonus claim blocks!");
                    pla.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }

            }
        }


        return true;
    }
}
