package net.shini9000.devtestPoint.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
    public static String getColour(String msg) {
        return msg == null ? "" : ChatColor.translateAlternateColorCodes('&', msg);
    }
}
