package net.shini9000.devtestPoint;

import net.shini9000.devtestPoint.commands.Points;
import net.shini9000.devtestPoint.listeners.PlayerJoin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class DevtestPoint extends JavaPlugin {
    PluginDescriptionFile descriptionFile = this.getDescription();

    @Override
    public void onEnable() {
        this.getCommand("points").setExecutor(new Points(this));
        //this.getCommand("top").setExecutor(new Top(this));
        //this.getCommand("zone").setExecutor(new Zone(this));
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

        String[] folders = new String[]{"PlayerData"};

        for(String folderName : folders) {
            File dir = new File(this.getDataFolder(), folderName);
            if (!dir.exists()) {
                dir.mkdirs();
                this.getLogger().info("Created directory: " + dir.getPath());
            } else {
                this.getLogger().info("Directory already exists " + dir.getPath());
            }
        }

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.getLogger().info("Plugin loaded!");
    }

    public void onDisable() {
        this.getLogger().info("Plugin disabled!");

    }
}
