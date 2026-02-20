package net.shini9000.devtestPoint.listeners;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.SAND;

public class BlockMine implements Listener {
    private final DevtestPoint plugin;

    public BlockMine(DevtestPoint plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockMine(BlockBreakEvent e) {
        PlayerConfig pc = plugin.getPlayerConfig(e.getPlayer().getUniqueId());
        // add a method to check what block is broken if it is case true then break if not then dont give point...
        Material type = e.getBlock().getType();
        switch (type) {
            case SAND:
                pc.addPoints(1);
                break;
            case DIRT:
                pc.addPoints(1);
                break;
            default: pc.addPoints(1);
        }
        //Player p = e.getPlayer();
//        p.sendMessage("DEBUG BLOCK BREAK");
//        p.getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 1));
        //pc.addPoints(1);
    }
}
