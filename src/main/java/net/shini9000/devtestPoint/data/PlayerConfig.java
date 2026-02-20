package net.shini9000.devtestPoint.data;

import net.shini9000.devtestPoint.DevtestPoint;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {
    private final DevtestPoint plugin;
    private final File file;
    private FileConfiguration config;

    public PlayerConfig(DevtestPoint plugin, UUID uuid) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "PlayerData/" + uuid.toString() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void createFile(String playerName, UUID playerUUID) {
        Bukkit.getConsoleSender().sendMessage("Player data save request " + playerName + " -- " + String.valueOf(playerUUID));
        try {
            this.file.createNewFile();
            this.config.set("Username", playerName);
            this.config.set("UUID", playerUUID.toString());
            this.config.set("Currency.Points", 0);
            this.save();
            this.reload();
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("Player data failed  to save " + playerName + " -- " + String.valueOf(playerUUID));
        }

        Bukkit.getConsoleSender().sendMessage("Player data saved " + playerName + " -- " + String.valueOf(playerUUID));
    }

    public void checkData(String playerName, UUID playerUUID) {
        if (!this.file.exists()) {
            createFile(playerName, playerUUID);
            this.save();
            this.reload();
        }
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }


    public String getUsername() {
        return this.config.getString("Username");
    }

    public String getUUID() {
        return this.config.getString("UUID");
    }

    public int addPoints(int amount) {
        int current = this.config.getInt("Currency.Points");
        int updated = current + amount;

        this.config.set("Currency.Points", updated);
        save();

        return updated;
    }

    public int getPoints() {
        return this.config.getInt("Currency.Points");
    }


    public long getKitCooldown(String kitName) {
        return config.getLong("Cooldowns." + kitName, 0);
    }

    public void setKitCooldown(String kitName, long time) {
        config.set("Cooldowns." + kitName, time);
        save();
    }


}
