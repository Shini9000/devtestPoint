package net.shini9000.devtestPoint.listeners;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BlockMine implements Listener {
    private final DevtestPoint plugin;

    public BlockMine(DevtestPoint plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockMine(BlockBreakEvent e) {
        PlayerConfig pc = plugin.getPlayerConfig(e.getPlayer().getUniqueId());
        pc.addPoints(1);
    }
}
