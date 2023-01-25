package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.Map;
import com.kryeit.commands.*;
import com.kryeit.tab.BasicPlayerTab;
import com.kryeit.tab.PlayerTab;
import com.kryeit.tab.ReturnEmptyTab;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Stuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI;
    public static List<UUID> sentTrapped;
    public static Stuff instance;
    public static List<UUID> warned;
    public static ArrayList<String> offlinePlayers;

    public void onEnable () {

        sentTrapped = new ArrayList<>();

        offlinePlayers = new ArrayList<>();

        afkPlusPlayerAPI = new AFKPlusPlayerAPI();

        warned = new ArrayList<>();

        instance = this;

        registerEvent(new onMessageSent());
        registerEvent(new onAFKToggle());
        registerEvent(new onJoin());
        registerEvent(new onBlockPlace());
        registerEvent(new onBlockInteract());
        registerEvent(new onWeatherChange());

        Objects.requireNonNull(getCommand("vr")).setExecutor(new VotingReward());

        registerBasicCommand("online", new Online());
        registerBasicCommand("discord", new Discord());
        registerBasicCommand("forum", new Forum());
        registerBasicCommand("rules", new Rules());
        registerBasicCommand("map", new Map());
        registerBasicCommand("vote", new Vote());
        registerBasicCommand("patreon", new Patreon());

        registerCommand("sendcoords", new SendCoords(), new BasicPlayerTab());
        registerCommand("timeplayed", new TimePlayed(), new BasicPlayerTab());

        registerCommand("lastonline", new LastOnline(), new PlayerTab());


    }

    public void registerEvent (Listener listener) {
        getServer().getPluginManager().registerEvents(listener,this);
    }

    public void registerCommand (String command, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(tabCompleter);
    }

    public void registerBasicCommand (String command, CommandExecutor commandExecutor) {
        Objects.requireNonNull(getCommand(command)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(new ReturnEmptyTab());
    }

    public void onDisable() {
    }

    public static Stuff getInstance() {
        return instance;
    }
}
