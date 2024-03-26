package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Collection;
public class PlayerDamaged implements Listener {
    @EventHandler
    public void damagedByEntity(EntityDamageByEntityEvent e) {
        if(e.getEntityType() == EntityType.PLAYER ) {
            //e.setCancelled(true);
            Collection<? extends Player> players2 = Bukkit.getOnlinePlayers();
            //Collection<Villager> players = e.getEntity().getWorld().getEntitiesByClass(Villager.class);
            //double damageEach = e.getFinalDamage() / (players.size() + players2.size());
            double damageEach = e.getFinalDamage() / players2.size();
            ((LivingEntity)(e.getEntity())).damage(damageEach);
            e.setDamage(0);
            /*for (LivingEntity player : players) {
                player.damage(damageEach, e.getDamager());
                // Do something with each player
            }*/
            for (Player player2 : players2) {
                player2.damage(damageEach, e.getDamager());
                // Do something with each player
            }
        }
    }

    @EventHandler
    public void damagedNonEntity(EntityDamageEvent e) {
        if(e.getEntityType() == EntityType.PLAYER && (e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK && e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION &&  e.getCause() != EntityDamageEvent.DamageCause.CUSTOM)) {
            //e.setCancelled(true);
            Collection<? extends Player> players2 = Bukkit.getOnlinePlayers();
            //Collection<Villager> players = e.getEntity().getWorld().getEntitiesByClass(Villager.class);
            //double damageEach = e.getFinalDamage() / (players.size() + players2.size());
            double damageEach = e.getFinalDamage() / players2.size();
            e.setDamage(0);
            /*for (LivingEntity player : players) {
                player.damage(damageEach);
                // Do something with each player
            }*/
            for (Player player2 : players2) {
                player2.damage(damageEach);
                // Do something with each player
            }
        }
    }
}