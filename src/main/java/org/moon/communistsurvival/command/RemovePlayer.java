package org.moon.communistsurvival.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.moon.communistsurvival.playerList;

public class RemovePlayer implements CommandExecutor { //명령어 처리 클래스는 CommandExecutor 인터페이스를 상속해야 한다.

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                if(sender instanceof Player) {
                    playerList.comrades.remove(((Player) sender).getName());
                    sender.sendMessage(String.format("%s is removed.",((Player) sender).getName()));
                }
                else {
                    sender.sendMessage("Unexpected Arguments.");
                    return false;
                }
                break;
            case 1:
                playerList.comrades.remove(args[0]);
                sender.sendMessage(String.format("%s is removed.",args[0]));
                break;
            default:
                return false;
        }

        return true;
    }

}
