package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.Map;
import com.kryeit.commands.*;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Stuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI;
    public static List<UUID> sent;
    public static Stuff instance;
    public static List<UUID> warned;
    public static Set<OfflinePlayer> offlinePlayers;

    public void onEnable() {

        sent = new ArrayList<>();

        offlinePlayers = new HashSet<>();

        offlinePlayers.addAll(Arrays.asList(Bukkit.getServer().getOfflinePlayers()));

        afkPlusPlayerAPI = new AFKPlusPlayerAPI();

        warned = new ArrayList<>();

        instance = this;

        getServer().getPluginManager().registerEvents(new onMessageSent(), this);
        getServer().getPluginManager().registerEvents(new onAFKToggle(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new onBlockInteract(), this);
        getServer().getPluginManager().registerEvents(new onWeatherChange(), this);


        Objects.requireNonNull(getCommand("vr")).setExecutor(new VotingReward());

        Objects.requireNonNull(getCommand("online")).setExecutor(new Online());
        Objects.requireNonNull(getCommand("online")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("discord")).setExecutor(new Discord());
        Objects.requireNonNull(getCommand("discord")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("forum")).setExecutor(new Forum());
        Objects.requireNonNull(getCommand("forum")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("rules")).setExecutor(new Rules());
        Objects.requireNonNull(getCommand("rules")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("map")).setExecutor(new Map());
        Objects.requireNonNull(getCommand("map")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("sendcoords")).setExecutor(new SendCoords());
        Objects.requireNonNull(getCommand("sendcoords")).setTabCompleter(new BasicPlayerTab());

        Objects.requireNonNull(getCommand("vote")).setExecutor(new Vote());
        Objects.requireNonNull(getCommand("vote")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("patreon")).setExecutor(new Patreon());
        Objects.requireNonNull(getCommand("patreon")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("timeplayed")).setExecutor(new TimePlayed());
        Objects.requireNonNull(getCommand("timeplayed")).setTabCompleter(new BasicPlayerTab());

        Objects.requireNonNull(getCommand("lastonline")).setExecutor(new LastOnline());
        Objects.requireNonNull(getCommand("lastonline")).setTabCompleter(new PlayerTab());

    }

    public void onDisable() {
    }

    public static Stuff getInstance() {
        return instance;
    }
}
