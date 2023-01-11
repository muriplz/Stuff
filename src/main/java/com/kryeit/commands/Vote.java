package com.kryeit.commands;

import com.kryeit.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        TextComponent message = new TextComponent(Utils.color("Planet Minecraft -> &9&nhttps://www.planetminecraft.com"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.planetminecraft.com/server/kryeit-5584167/vote/"));
        player.spigot().sendMessage(message);
        return true;
    }
}
