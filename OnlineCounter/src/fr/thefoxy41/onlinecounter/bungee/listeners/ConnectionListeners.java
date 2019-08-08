package fr.thefoxy41.onlinecounter.bungee.listeners;

import fr.thefoxy41.onlinecounter.commons.Counter;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void on(PostLoginEvent event) {
        Counter.update(BungeeCord.getInstance().getOnlineCount());
    }

    @EventHandler
    public void on(PlayerDisconnectEvent event) {
        // check if player is in count or not
        Counter.update(BungeeCord.getInstance().getOnlineCount() - 1);
    }
}
