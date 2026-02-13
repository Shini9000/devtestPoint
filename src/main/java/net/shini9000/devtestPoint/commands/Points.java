package net.shini9000.devtestPoint.commands;

import net.shini9000.devtestPoint.DevtestPoint;
import net.shini9000.devtestPoint.listeners.PlayerJoin;
import net.shini9000.devtestPoint.utils.ChatUtils;
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
    public boolean onCommand(CommandSender sender, Command cmd, String args, String[] strings) {
        if (sender instanceof ConsoleCommandSender) {
            // check for playername
        } else {
            // check for args will be false anyway so return error message and return
            return true;
        }

        if (sender instanceof Player) {
            Player p = (Player) sender;
            // check for args
            if (args.length() > 0) {
                p.sendMessage("DEBUG RETURN");
            }
            p.sendMessage("DEBUG RETURN NO ARGS");
        } else {
            return true;
        }
        return false;
    }
}
