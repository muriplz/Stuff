package com.kryeit.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;


public class MuteTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 3) return List.of("[days]");

        if(args.length == 1) return List.of("soft","hard");

        List<String> list = new ArrayList<>();

        if(args.length == 2) {
            Bukkit.getOnlinePlayers().forEach(p -> list.add(p.getName()));
            return list;
        }


        return List.of();
    }
}
