package net.shini9000.devtestPoint.listeners;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final DevtestPoint plugin;

    public PlayerJoin(DevtestPoint plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            PlayerConfig pc = new PlayerConfig(this.plugin, p.getUniqueId());
            pc.createFile(p.getName(), p.getUniqueId());
        }
    }
}
