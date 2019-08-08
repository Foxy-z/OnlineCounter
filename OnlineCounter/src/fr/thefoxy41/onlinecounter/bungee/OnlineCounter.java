package fr.thefoxy41.onlinecounter.bungee;

import fr.thefoxy41.onlinecounter.bungee.listeners.ConnectionListeners;
import fr.thefoxy41.onlinecounter.commons.GlobalConfiguration;
import fr.thefoxy41.onlinecounter.commons.database.DatabaseManager;
import fr.thefoxy41.onlinecounter.commons.interfaces.Plugin;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.PluginManager;

public class OnlineCounter extends net.md_5.bungee.api.plugin.Plugin implements Plugin {
    public static OnlineCounter INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        GlobalConfiguration.init(this);

        try {
            DatabaseManager.initDataBaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            log("An error occurred while enabling the plugin, it has been disabled");
            this.onDisable();
            return;
        }

        // register events
        PluginManager manager = BungeeCord.getInstance().getPluginManager();
        manager.registerListener(this, new ConnectionListeners());

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
        return "BungeeCord";
    }

    public void log(String message) {
        BungeeCord.getInstance().getConsole().sendMessage("[" + INSTANCE.getDescription().getName() + "] " + message);
    }

}
