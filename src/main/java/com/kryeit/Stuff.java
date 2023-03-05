package com.kryeit;

import com.kryeit.Listener.*;
import com.kryeit.commands.Map;
import com.kryeit.commands.*;
import com.kryeit.storage.Properties.DiskMap;
import com.kryeit.tab.BasicPlayerTab;
import com.kryeit.tab.MuteTab;
import com.kryeit.tab.PlayerTab;
import com.kryeit.tab.ReturnEmptyTab;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.nio.file.Paths;
import java.util.*;

public class Stuff extends JavaPlugin {

    public static AFKPlusPlayerAPI afkPlusPlayerAPI = new AFKPlusPlayerAPI();
    public final List<UUID> sentTrapped = new ArrayList<>();
    public static Stuff instance;
    public final List<UUID> warned = new ArrayList<>();
    public final List<UUID> flyEnabled = new ArrayList<>();
    public final List<String> offlinePlayers = new ArrayList<>();

    public DiskMap softMuted;
    public DiskMap hardMuted;


    public void onEnable () {
        softMuted = new DiskMap(Paths.get("plugins/Stuff/softMuted.yml"));
        hardMuted = new DiskMap(Paths.get("plugins/Stuff/hardMuted.yml"));

        instance = this;

        registerEvent(new onMessageSent());
        registerEvent(new onAFKToggle());
        registerEvent(new onJoin());
        registerEvent(new onBlockPlace());
        registerEvent(new onBlockInteract());
        registerEvent(new onWeatherChange());
        registerEvent(new onMutedMessage());

        Objects.requireNonNull(getCommand("vr")).setExecutor(new VotingReward());

        registerBasicCommand("online", new Online());
        registerBasicCommand("discord", new Discord());
        registerBasicCommand("forum", new Forum());
        registerBasicCommand("rules", new Rules());
        registerBasicCommand("map", new Map());
        registerBasicCommand("vote", new Vote());
        registerBasicCommand("patreon", new Patreon());
        registerBasicCommand("fly", new Fly());
        registerBasicCommand("getinventory", new GetInventory());

        registerCommand("sendcoords", new SendCoords(), new BasicPlayerTab());
        registerCommand("timeplayed", new TimePlayed(), new BasicPlayerTab());
        registerCommand("mute", new Mute(), new MuteTab());

        registerCommand("lastonline", new LastOnline(), new PlayerTab());

        unmutePlayers();

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

    public void unmutePlayers() {
        // Get the current time
        Calendar now = Calendar.getInstance();

        // Set the time for the function to run (12:00 PM)
        Calendar nextRun = Calendar.getInstance();
        nextRun.set(Calendar.HOUR_OF_DAY, 12);
        nextRun.set(Calendar.MINUTE, 0);
        nextRun.set(Calendar.SECOND, 0);

        // If it's already past the scheduled time for today, schedule it for tomorrow instead
        if (now.after(nextRun)) {
            nextRun.add(Calendar.DATE, 1);
        }

        // Calculate the delay until the next scheduled run
        long delay = nextRun.getTimeInMillis() - now.getTimeInMillis();

        // Schedule the function to run every 24 hours
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, this::deleteFromBothMutes, delay, 24 * 60 * 60 * 20);
    }

    public void deleteFromBothMutes() {
        for(byte[] muted : softMuted.keySet()) {
            softMuted.remove(muted);
            softMuted.put(muted,DiskMap.intToByteArray(DiskMap.byteArrayToInt(softMuted.get(muted)) - 1));
        }
        for(byte[] muted : hardMuted.keySet()) {
            hardMuted.remove(muted);
            hardMuted.put(muted,DiskMap.intToByteArray(DiskMap.byteArrayToInt(hardMuted.get(muted)) - 1));
        }
    }

    public void onDisable() {
    }

    public static Stuff getInstance() {
        return instance;
    }
}
