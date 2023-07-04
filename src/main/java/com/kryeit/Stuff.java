package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.*;
import com.kryeit.commands.Map;
import com.kryeit.tab.BasicPlayerTab;
import com.kryeit.tab.BuyLightTab;
import com.kryeit.tab.PlayerTab;
import com.kryeit.tab.ReturnEmptyTab;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Stuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI = new AFKPlusPlayerAPI();
    public final List<UUID> sentTrapped = new ArrayList<>();
    public static Stuff instance;
    public final List<UUID> warned = new ArrayList<>();
    public final List<UUID> flyEnabled = new ArrayList<>();
    public final List<String> offlinePlayers = new ArrayList<>();

    public HashMap<UUID, Location> seatLocation = new HashMap<>();

    public void onEnable () {

        instance = this;

        registerEvent(new onMessageSent());
        registerEvent(new onAFKToggle());
        registerEvent(new onJoin());
        registerEvent(new onBlockPlace());
        registerEvent(new onBlockInteract());
        registerEvent(new onWeatherChange());
        registerEvent(new onInvInteract());
        registerEvent(new onItemPickUp());
        registerEvent(new onEndermanTake());
        registerEvent(new onChickenEgg());
        registerEvent(new onDeath());
        registerEvent(new onEnzoMove());
        registerEvent(new onTrainLeave());

        Objects.requireNonNull(getCommand("vr")).setExecutor(new VotingReward());

        registerBasicCommand("online", new Online());
        registerBasicCommand("discord", new Discord());
        registerBasicCommand("rules", new Rules());
        registerBasicCommand("map", new Map());
        registerBasicCommand("vote", new Vote());
        registerBasicCommand("kofi", new KoFi());
        registerBasicCommand("fly", new Fly());

        registerCommand("invsee", new InvSee(), new BasicPlayerTab());
        registerCommand("enderinvsee", new EnderInvSee(), new BasicPlayerTab());
        registerCommand("sendcoords", new SendCoords(), new BasicPlayerTab());
        registerCommand("timeplayed", new TimePlayed(), new BasicPlayerTab());
        registerCommand("buylight", new BuyLight(), new BuyLightTab());

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
