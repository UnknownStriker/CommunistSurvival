package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.moon.communistsurvival.CommunistSurvival;
import org.moon.communistsurvival.playerList;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerRecovered implements Listener {
    @EventHandler
    public void Healed(EntityRegainHealthEvent e) {

        if(e.getEntityType() != EntityType.PLAYER) return;

        Collection<Player> players = Bukkit.getOnlinePlayers().stream() //분배 대상 플레이어들 수집
                .filter(player -> playerList.comrades.contains(player.getName()))
                .collect(Collectors.toList());
        if(!playerList.comrades.contains(e.getEntity().getName())) return;

        boolean pvpMode = playerList.isPvp;
        double damageEach;
        if(pvpMode) {
            damageEach = e.getAmount() / 2;
        }
        else {
            damageEach = e.getAmount() / players.size();
            e.setAmount(damageEach);

        }

        for (Player player2 : players) {
            if(player2 != e.getEntity() && !player2.isDead()) {
                player2.setHealth(Math.min(damageEach + player2.getHealth(), Objects.requireNonNull(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
                // Do something with each player
            }
        }
    }
}
