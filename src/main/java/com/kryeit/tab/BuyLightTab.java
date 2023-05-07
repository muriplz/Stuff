package com.kryeit.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class BuyLightTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            return List.of("<intensity>");
        }
        else if(args.length == 2) {
            return List.of("<amount>");
        }
        return List.of();
    }
}
