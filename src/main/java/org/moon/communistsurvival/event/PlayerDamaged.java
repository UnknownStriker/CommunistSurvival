package org.moon.communistsurvival.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.moon.communistsurvival.CommunistSurvival;
import org.moon.communistsurvival.playerList;

import java.util.Collection;
import java.util.stream.Collectors;

public class PlayerDamaged implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return; //플레이어가 아니면 리턴
        boolean pvpMode = playerList.isPvp; //PVP 모드인지 확인
        Collection<Player> players = Bukkit.getOnlinePlayers().stream() //분배 대상 플레이어들 수집
                .filter(player -> playerList.comrades.contains(player.getName()))
                .collect(Collectors.toList());
        if(!playerList.comrades.contains(e.getEntity().getName())) return;

        if (e instanceof EntityDamageByEntityEvent) { //엔티티한테 얻어맞았으면
            EntityDamageByEntityEvent entityDamage = (EntityDamageByEntityEvent) e;
            handleEntityDamageByEntity(entityDamage, players, pvpMode);
        } else { //아니면
            handleNonEntityDamage(e, players, pvpMode);
        }
    }

    private void handleEntityDamageByEntity(EntityDamageByEntityEvent e, Collection<Player> players, boolean pvpMode) {
        if (pvpMode) { //PVP 모드면
            double damageUnrelated = e.getFinalDamage() / 4; //관련 X -> 25%
            double damageCauser = e.getFinalDamage() / 2; // 내가 때림 -> 50%
            for (Player player : players) {
                if (player != e.getEntity() && !player.isDead()) {
                    if (player == e.getDamager() || (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() == player)) {
                        player.damage(damageCauser);
                        player.sendMessage(String.format("You got %.2f damage by attacking %s", damageCauser, e.getEntity().getName()));
                    } else {
                        player.damage(damageUnrelated);
                    }
                }
            }
        } else { //아니면
            distributeDamageEqually(e, players); //데미지 분배
        }
    }

    private void handleNonEntityDamage(EntityDamageEvent e, Collection<Player> players, boolean pvpMode) {
        if(e.getCause() == EntityDamageEvent.DamageCause.CUSTOM) return;
        if (pvpMode) { //PVP 모드면
            for (Player player : players) {
                if (player != e.getEntity() && !player.isDead()) {
                    player.damage(e.getFinalDamage() / 4); // 25% 데미지
                }
            }
        } else {
            distributeDamageEqually(e, players); //데미지 분배
        }
    }

    private void distributeDamageEqually(EntityDamageEvent e, Collection<Player> players) {
        double damageEach = e.getFinalDamage() / players.size();
        e.setDamage(damageEach);
        for (Player player : players) {
            if (player != e.getEntity() && !player.isDead()) {
                player.damage(damageEach);
            }
        }
    }
}
