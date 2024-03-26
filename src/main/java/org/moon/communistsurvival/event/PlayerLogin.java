package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Collection;
import java.util.Comparator;

public class PlayerLogin implements Listener {
    @EventHandler
    public void PlayerJoined(PlayerLoginEvent e) {
        if(e.getResult() == PlayerLoginEvent.Result.ALLOWED) {
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            double minHealth = players.stream()
                    .min(Comparator.comparingDouble(Player::getHealth))
                    .get()
                    .getHealth();
            e.getPlayer().setHealth(minHealth);

        }
    }
}
