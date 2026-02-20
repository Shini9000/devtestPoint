package net.shini9000.devtestPoint.listeners;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerJoin implements Listener {
    private final DevtestPoint plugin;

    public PlayerJoin(DevtestPoint plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        plugin.loadPlayerConfig(uuid);

        PlayerConfig pc = plugin.getPlayerConfig(uuid);
        pc.checkData(e.getPlayer().getName(), uuid);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();

        PlayerConfig pc = plugin.getPlayerConfig(uuid);
        if (pc != null) {
            pc.save();
        }

        plugin.unloadPlayerConfig(uuid);
    }


}
