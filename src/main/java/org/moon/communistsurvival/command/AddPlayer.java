package org.moon.communistsurvival.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.moon.communistsurvival.playerList;

public class AddPlayer implements CommandExecutor { //명령어 처리 클래스는 CommandExecutor 인터페이스를 상속해야 한다.

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                if(sender instanceof Player) {
                    if(playerList.comrades.contains(((Player) sender).getName())) {
                        sender.sendMessage("It is already included");
                        return false;
                    }
                    playerList.comrades.add(((Player) sender).getName());
                    sender.sendMessage(String.format("%s is registered.",((Player) sender).getName()));
                }
                else {
                    sender.sendMessage("Unexpected Arguments.");
                    return false;
                }
                break;
            case 1:
                if(playerList.comrades.contains(args[0])) {
                    sender.sendMessage("It is already included");
                    return false;
                }
                playerList.comrades.add(args[0]);
                sender.sendMessage(String.format("%s is registered.",args[0]));
                break;
            default:
                return false;
        }

        return true;
    }

}
