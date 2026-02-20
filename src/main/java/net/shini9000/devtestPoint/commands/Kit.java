package net.shini9000.devtestPoint.commands;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import net.shini9000.devtestPoint.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Kit implements CommandExecutor {
    private final DevtestPoint plugin;

    public Kit(DevtestPoint plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ItemStack[] starter = {
                new ItemStack(Material.IRON_PICKAXE, 1),
                new ItemStack(Material.IRON_SHOVEL, 1),
                new ItemStack(Material.IRON_AXE, 1),
                new ItemStack(Material.TORCH, 64)
        };

        // CONSOLE
        if (sender instanceof ConsoleCommandSender) {

            if (args.length < 2) {
                sender.sendMessage("Usage: /kit starter <player>");
                return true;
            }

            if (!args[0].equalsIgnoreCase("starter")) {
                sender.sendMessage("Unknown kit.");
                return true;
            }

            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
            if (!target.isOnline()) {
                sender.sendMessage("Player not found or offline.");
                return true;
            }

            Player t = target.getPlayer();
            t.getInventory().addItem(starter);
            t.sendMessage(ChatUtils.getColour(plugin.getConfig().getString("Kit.Recieved")));

            sender.sendMessage("Gave starter kit to " + t.getName());
            return true;
        }


        // PLAYER
        Player p = (Player) sender;

        if (!p.hasPermission("devtest.kit")) {
            p.sendMessage(ChatUtils.getColour(plugin.getConfig().getString("Error.Perm")));
            return true;
        }

        // /kit
        if (args.length == 0) {
            p.sendMessage("Usage: /kit starter [player]");
            return true;
        }

        // /kit starter
        if (args[0].equalsIgnoreCase("starter")) {



            long now = System.currentTimeMillis();
            long cooldown = 86400000; // 24 hours

            // /kit starter <player>
            if (args.length == 2) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                Player t = target.getPlayer();
                if (!target.isOnline()) {
                    p.sendMessage("Player not found or offline.");
                    return true;
                }
                PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());

                long lastUsed = pc.getKitCooldown("starter");

                if (now - lastUsed < cooldown) {
                    long remaining = cooldown - (now - lastUsed);
                    long hours = remaining / 1000 / 60 / 60;
                    long minutes = (remaining / 1000 / 60) % 60;

                    t.sendMessage("You must wait " + hours + "h " + minutes + "m before using this kit again.");
                    return true;
                }

                t.getInventory().addItem(starter);

                pc.setKitCooldown("starter", now);
                t.sendMessage(ChatUtils.getColour(plugin.getConfig().getString("Kit.Recieved")));

                p.sendMessage("Gave starter kit to " + t.getName());
                return true;
            }

            PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());

            long lastUsed = pc.getKitCooldown("starter");
            if (now - lastUsed < cooldown) {
                long remaining = cooldown - (now - lastUsed);
                long hours = remaining / 1000 / 60 / 60;
                long minutes = (remaining / 1000 / 60) % 60;

                p.sendMessage("You must wait " + hours + "h " + minutes + "m before using this kit again.");
                return true;
            }

            // /kit starter (self)
            p.getInventory().addItem(starter);
            pc.setKitCooldown("starter", now);
            p.sendMessage(ChatUtils.getColour(plugin.getConfig().getString("Kit.Recieved")));
            return true;
        }

        // Unknown kit
        p.sendMessage("KIT NO EXIST");
        return true;
    }
}