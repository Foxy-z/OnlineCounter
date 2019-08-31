package fr.thefoxy41.onlinecounter.bukkit;

import fr.thefoxy41.onlinecounter.bukkit.listeners.ConnectionListeners;
import fr.thefoxy41.onlinecounter.commons.GlobalConfiguration;
import fr.thefoxy41.onlinecounter.commons.database.DatabaseManager;
import fr.thefoxy41.onlinecounter.commons.interfaces.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OnlineCounter extends JavaPlugin implements Plugin {

    @Override
    public void onEnable() {

        GlobalConfiguration.init(this);

        // try to connect to database
        try {
            DatabaseManager.initDataBaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            log("An error occurred while enabling the plugin, it has been disabled");
            getPluginLoader().disablePlugin(this);
            return;
        }

        // register events
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new ConnectionListeners(), this);

        log("Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        DatabaseManager.closeDataBaseConnection();
        log("Plugin has been disabled");
    }

    @Override
    public String getPluginFolder() {
        return this.getDataFolder().toString();
    }

    @Override
    public String getServerName() {
        return Bukkit.getServerName();
    }

    @Override
    public void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage("[" + this.getName() + "] " + message);
    }
}
