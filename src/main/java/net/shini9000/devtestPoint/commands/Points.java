package net.shini9000.devtestPoint.commands;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.data.PlayerConfig;
import net.shini9000.devtestPoint.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Points implements CommandExecutor {
    private final DevtestPoint plugin;
    public Points(DevtestPoint plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Console handling
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                sender.sendMessage("Console must specify a player name.");
                return true;
            }

            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Player not found.");
                    return true;
                } else {
                    target.getUniqueId();
                }

                PlayerConfig pc = new PlayerConfig(plugin, target.getUniqueId());
                sender.sendMessage(ChatUtils.getColour("&e" + target.getName() + " " + this.plugin.getConfig().getString("PlayerPoints").
                        replace("%player_points%", String.valueOf(pc.getPoints()))));
                return true;
            }
            return true;
        }

        // Player handling
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("devtest.points")) {
                p.sendMessage(ChatUtils.getColour(this.plugin.getConfig().getString("Error.Perm")));
                return true;
            }

            if (args.length > 0) {
                OfflinePlayer target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    p.sendMessage("Player not found.");
                    return true;
                } else {
                    target.getUniqueId();
                }

                PlayerConfig pc = new PlayerConfig(plugin, target.getUniqueId());
                p.sendMessage(ChatUtils.getColour("&e" + target.getName() + " " + this.plugin.getConfig().getString("PlayerPoints").
                        replace("%player_points%", String.valueOf(pc.getPoints()))));
                return true;
            }

            PlayerConfig pc = new PlayerConfig(plugin, p.getUniqueId());
            p.sendMessage(ChatUtils.getColour(this.plugin.getConfig().getString("Points").
                    replace("%player_points%", String.valueOf(pc.getPoints()))));
            return true;
        }

        return true;
    }
}
