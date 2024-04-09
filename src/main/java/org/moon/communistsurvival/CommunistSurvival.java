package org.moon.communistsurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.moon.communistsurvival.command.AddPlayer;
import org.moon.communistsurvival.command.ListPlayers;
import org.moon.communistsurvival.command.RemovePlayer;
import org.moon.communistsurvival.command.SetMode;
import org.moon.communistsurvival.event.PlayerDamaged;
import org.moon.communistsurvival.event.PlayerLogin;
import org.moon.communistsurvival.event.PlayerRecovered;
import org.moon.communistsurvival.playerList;


public final class CommunistSurvival extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Communist Survival Started");
        this.saveDefaultConfig();
        playerList.isPvp = this.getConfig().getBoolean("PvpMode");
        getLogger().info(String.format("Current Pvp state: %b", playerList.isPvp));
        getServer().getPluginManager().registerEvents(new PlayerDamaged(), this);
        getServer().getPluginManager().registerEvents(new PlayerRecovered(), this);
        getServer().getPluginManager().registerEvents(new PlayerLogin(), this);
        getServer().getPluginCommand("AddPlayer").setExecutor(new AddPlayer());
        getServer().getPluginCommand("RemovePlayer").setExecutor(new RemovePlayer());
        getServer().getPluginCommand("ListPlayers").setExecutor(new ListPlayers());
        getServer().getPluginCommand("SetMode").setExecutor(new SetMode());

    }

    @Override
    public void onDisable() {
        getLogger().info("Communist Survival Finished");
    }
}
