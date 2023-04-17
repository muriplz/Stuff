package com.kryeit.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCoords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player cordSender)) {
            Bukkit.getConsoleSender().sendMessage("You can't execute this command from console.");
            return false;
        }

        int x = cordSender.getLocation().getBlockX();
        int y = cordSender.getLocation().getBlockY();
        int z = cordSender.getLocation().getBlockZ();

        if (args.length != 1) {
            cordSender.sendMessage("You have to enter a player's name");
            return false;
        }

        Player cordReciever = Bukkit.getPlayer(args[0]);

        if (cordReciever == null) {
            cordSender.sendMessage("Player not found");
            return false;
        }

        BaseComponent[] components = new ComponentBuilder("")
                .append(new TextComponent(cordSender.getName()))
                .color(ChatColor.GOLD)
                .bold(true)
                .append(" has sent you the current coords: ")
                .color(ChatColor.WHITE)
                .append(new TextComponent("(" + x + ", " + y + ", " + z + ")"))
                .color(ChatColor.GOLD)
                .underlined(true)
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to see in Dynmap")
                        .color(ChatColor.LIGHT_PURPLE)
                        .create()))
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://map.kryeit.com/#world:" +
        x + ":" + y + ":" + z + ":" + cordSender.getLocation().getYaw() + ":" + cordSender.getLocation().getPitch() +
                ":0:0:0"))
                .create();

        cordReciever.spigot().sendMessage(components);
        cordSender.sendMessage(ChatColor.GOLD + cordReciever.getName() + ChatColor.WHITE + " has received your coords.");
        return true;
    }
}

