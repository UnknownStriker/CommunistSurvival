package org.moon.communistsurvival.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.moon.communistsurvival.CommunistSurvival;
import org.moon.communistsurvival.playerList;

public class SetMode implements CommandExecutor {

    private static final String PVP_MODE_KEY = "PvpMode";
    private static final String COOP_MODE_ENABLED_MESSAGE = ChatColor.GREEN + "Co-op mode enabled";
    private static final String PVP_MODE_ENABLED_MESSAGE = ChatColor.GREEN + "Pvp mode enabled";
    private static final String INVALID_ARGUMENTS_MESSAGE = ChatColor.RED + "Invalid arguments. Use /setmode <coop|pvp>";
    private static final String NO_PERMISSION_MESSAGE = ChatColor.RED + "You don't have permission to execute this command.";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(INVALID_ARGUMENTS_MESSAGE);
            return false; // Return true to indicate that a message has been sent to the sender
        }

        if (!sender.hasPermission("communistsurvival.setmode")) {
            sender.sendMessage(NO_PERMISSION_MESSAGE);
            return false;
        }

        boolean isPvpMode = "pvp".equalsIgnoreCase(args[0]);

        if (isPvpMode || "coop".equalsIgnoreCase(args[0])) {
            playerList.isPvp = isPvpMode;
            sender.sendMessage(isPvpMode ? PVP_MODE_ENABLED_MESSAGE : COOP_MODE_ENABLED_MESSAGE);
        } else {
            sender.sendMessage(INVALID_ARGUMENTS_MESSAGE);
            return false;
        }

        return true;
    }

}
