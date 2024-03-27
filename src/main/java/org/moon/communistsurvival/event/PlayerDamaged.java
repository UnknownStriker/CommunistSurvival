package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.moon.communistsurvival.CommunistSurvival;

import java.util.Collection;
public class PlayerDamaged implements Listener {
    @EventHandler
    public void damagedByEntity(EntityDamageByEntityEvent e) {
        if(e.getEntityType() == EntityType.PLAYER) {
            boolean pvpMode = CommunistSurvival.getPlugin(CommunistSurvival.class).getConfig().getBoolean("PvpMode");
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            if(pvpMode) {
                double damageUnrelated = e.getFinalDamage() / 4;
                double damageCauser = e.getFinalDamage() / 2;
                for (Player player2 : players) {
                    if(player2 != e.getEntity() && !player2.isDead()) {
                        if(player2 == e.getDamager() || (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() == player2)) {
                            player2.damage(damageCauser);
                            player2.sendMessage(String.format("You got %.2f damage by attacking %s", damageCauser, e.getEntity().getName()));
                        }
                        else {
                            player2.damage(damageUnrelated);
                        }
                    }
                }
            }
            else {
                double damageEach = e.getFinalDamage() / players.size();
                e.setDamage(damageEach);
                for (Player player2 : players) {
                    if(player2 != e.getEntity() && !player2.isDead()) {
                        player2.damage(damageEach);
                    }
                }
            }

        }
    }

    @EventHandler
    public void damagedNonEntity(EntityDamageEvent e) {
        if(e.getEntityType() == EntityType.PLAYER && (e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK && e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION &&  e.getCause() != EntityDamageEvent.DamageCause.CUSTOM)) {
            Collection<? extends Player> players2 = Bukkit.getOnlinePlayers();
            boolean pvpMode = CommunistSurvival.getPlugin(CommunistSurvival.class).getConfig().getBoolean("PvpMode");
            double damageEach;
            if(pvpMode) {
                damageEach = e.getFinalDamage() / 4;
            }
            else {
                damageEach = e.getFinalDamage() / players2.size();
                e.setDamage(damageEach);
            }
            for (Player player2 : players2) {
                if(player2 != e.getEntity() && !player2.isDead()) {
                    player2.damage(damageEach);
                }
                // Do something with each player
            }
        }
    }
}