package org.moon.communistsurvival.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.moon.communistsurvival.playerList;

public class ListPlayers implements CommandExecutor { //명령어 처리 클래스는 CommandExecutor 인터페이스를 상속해야 한다.

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        sender.sendMessage("Current Registered Players: ");
        for (String player : playerList.comrades) {
            sender.sendMessage(player);
        }
        return true;
    }

}
