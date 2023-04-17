package com.kryeit.commands;

import com.kryeit.Utils;
import net.lapismc.afkplus.playerdata.AFKPlusPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.kryeit.Stuff.afkPlusPlayerAPI;

public class VotingReward implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) return false;
        if (args.length != 2) return false;

        String name = args[0];
        Player player = Bukkit.getPlayer(name);

        if(player == null) return false;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gd player adjustbonusblocks " + player.getName() + " " + 100);
        player.sendMessage(Utils.color("&7You voted! +100 bonus claim blocks!"));

        AFKPlusPlayer afkPlusPlayer;
        int reward = Integer.parseInt(args[1]);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(p.getName().equals(name)) continue;

            afkPlusPlayer = afkPlusPlayerAPI.getPlayer(p.getUniqueId());
            if(afkPlusPlayer.isAFK()) continue;

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gd player adjustbonusblocks " + p.getName() + " " + reward);
            String message = Utils.color("&7Someone voted... +"+reward+" bonus claim blocks!");
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }


        return true;
    }
}
