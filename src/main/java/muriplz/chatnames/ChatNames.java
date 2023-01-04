package muriplz.chatnames;

import muriplz.chatnames.Listener.*;
import muriplz.chatnames.commands.*;
import muriplz.chatnames.tabCompletion.ReturnEmpty;
import muriplz.chatnames.tabCompletion.SendCoordsCompletion;
import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ChatNames extends JavaPlugin {

    public static AFKPlusPlayerAPI api;

    public static List<UUID> sent;
    public void onEnable() {

        sent = new ArrayList<>();

        api = new AFKPlusPlayerAPI();
        getServer().getPluginManager().registerEvents(new onMessageSent(), this);
        getServer().getPluginManager().registerEvents(new afk(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onLavaOrFirePlace(), this);
        getServer().getPluginManager().registerEvents(new onLavaPlace(), this);


        Objects.requireNonNull(getCommand("vr")).setExecutor(new VotingReward());

        Objects.requireNonNull(getCommand("online")).setExecutor(new Online());
        Objects.requireNonNull(getCommand("online")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("discord")).setExecutor(new Discord());
        Objects.requireNonNull(getCommand("discord")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("forum")).setExecutor(new Forum());
        Objects.requireNonNull(getCommand("forum")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("rules")).setExecutor(new Rules());
        Objects.requireNonNull(getCommand("rules")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("map")).setExecutor(new Map());
        Objects.requireNonNull(getCommand("map")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("sendcoords")).setExecutor(new SendCoords());
        Objects.requireNonNull(getCommand("sendcoords")).setTabCompleter(new SendCoordsCompletion());

        Objects.requireNonNull(getCommand("vote")).setExecutor(new Vote());
        Objects.requireNonNull(getCommand("vote")).setTabCompleter(new ReturnEmpty());

        Objects.requireNonNull(getCommand("patreon")).setExecutor(new Patreon());
        Objects.requireNonNull(getCommand("patreon")).setTabCompleter(new ReturnEmpty());


    }

    public void onDisable() {
    }
}
