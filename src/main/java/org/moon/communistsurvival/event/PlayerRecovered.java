package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.moon.communistsurvival.CommunistSurvival;

import java.util.Collection;
import java.util.Objects;

public class PlayerRecovered implements Listener {
    @EventHandler
    public void Healed(EntityRegainHealthEvent e) {

        if(e.getEntityType() == EntityType.PLAYER) {
            Collection<? extends Player> players2 = Bukkit.getOnlinePlayers();
            boolean pvpMode = CommunistSurvival.getPlugin(CommunistSurvival.class).getConfig().getBoolean("PvpMode");
            double damageEach;
            if(pvpMode) {
                damageEach = e.getAmount() / 2;
            }
            else {
                damageEach = e.getAmount() / players2.size();
                e.setAmount(damageEach);

            }

            for (Player player2 : players2) {
                if(player2 != e.getEntity() && !player2.isDead()) {
                    player2.setHealth(Math.min(damageEach + player2.getHealth(), Objects.requireNonNull(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
                    // Do something with each player
                }
            }
        }
    }
}
