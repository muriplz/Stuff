package com.kryeit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            // Initialize Lists

            // completions is the returned Lists, starts empty
            List<String> completions = new ArrayList<>();

            // Initialize allTabs
            List<String> allTabs = new ArrayList<>();

            // Get the name of all online players and add it to allTabs
            Bukkit.getOnlinePlayers().forEach(p -> allTabs.add(p.getName()));

            Arrays.stream(Bukkit.getOfflinePlayers()).iterator().forEachRemaining(p -> allTabs.add(p.getName()));

            // Add to "completions" all words that have letters that are contained on "commands" list
            for (String allTab : allTabs) {
                if (allTab.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(allTab);
                }
            }

            return completions;
        }
        return new ArrayList<>();
    }
}
