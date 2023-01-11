package com.kryeit.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class onWeatherChange implements Listener {

    @EventHandler
    public void onChange(WeatherChangeEvent e) {
        if(e.toWeatherState()) //returns true if its going to be raining
            e.setCancelled(ThreadLocalRandom.current().nextBoolean()); //50 / 50 chance of letting it rain
    }
}
