package org.moon.communistsurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.moon.communistsurvival.event.PlayerDamaged;
import org.moon.communistsurvival.event.PlayerLogin;
import org.moon.communistsurvival.event.PlayerRecovered;

public final class CommunistSurvival extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Communist Survival Started");
        getServer().getPluginManager().registerEvents(new PlayerDamaged(), this);
        getServer().getPluginManager().registerEvents(new PlayerRecovered(), this);
        getServer().getPluginManager().registerEvents(new PlayerLogin(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Communist Survival Finished");
    }
}
