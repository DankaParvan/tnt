package ru.danka.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class TntRun extends JavaPlugin {



    @Override
    public void onEnable() {





        Bukkit.getPluginManager().registerEvents(new Listener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
