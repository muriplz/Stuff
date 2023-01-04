package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.*;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Stuff extends JavaPlugin {

    public static AFKPlusPlayerAPI api;

    public static List<UUID> sent;
    public void onEnable() {

        sent = new ArrayList<>();

        api = new AFKPlusPlayerAPI();
        getServer().getPluginManager().registerEvents(new onMessageSent(), this);
        getServer().getPluginManager().registerEvents(new onAFKToggle(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new onBlockInteract(), this);


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
        Objects.requireNonNull(getCommand("sendcoords")).setTabCompleter(new SendCoordsCompletionTab());

        Objects.requireNonNull(getCommand("vote")).setExecutor(new Vote());
        Objects.requireNonNull(getCommand("vote")).setTabCompleter(new ReturnEmptyTab());

        Objects.requireNonNull(getCommand("patreon")).setExecutor(new Patreon());
        Objects.requireNonNull(getCommand("patreon")).setTabCompleter(new ReturnEmptyTab());


    }

    public void onDisable() {
    }
}
