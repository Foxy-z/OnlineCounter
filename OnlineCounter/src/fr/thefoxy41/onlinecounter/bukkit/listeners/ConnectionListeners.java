package fr.thefoxy41.onlinecounter.bukkit.listeners;

import fr.thefoxy41.onlinecounter.commons.Counter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Counter.update(Bukkit.getServer().getOnlinePlayers().size());
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Counter.update(Bukkit.getServer().getOnlinePlayers().size() - 1);
    }
}
