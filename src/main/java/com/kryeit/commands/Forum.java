package com.kryeit.commands;

import com.kryeit.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Forum implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent message = new TextComponent(Utils.color("Forum -> &9&nhttps://forum.kryeit.com"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://forum.kryeit.com"));
        Player player = (Player) sender;
        player.spigot().sendMessage(message);
        return false;
    }
}
