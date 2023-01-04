package com.kryeit.commands;

import com.kryeit.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Patreon implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent message = new TextComponent(Utils.color("Patreon -> &9&nhttps://www.patreon.com/KryeitMC"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.patreon.com/KryeitMC"));
        Player player = (Player) sender;
        player.spigot().sendMessage(message);
        return false;
    }

}
