package net.pl3x.bukkit.spawnereggcontrol;

import net.pl3x.bukkit.spawnereggcontrol.command.CmdSpawnerEggControl;
import net.pl3x.bukkit.spawnereggcontrol.configuration.Config;
import net.pl3x.bukkit.spawnereggcontrol.configuration.Lang;
import net.pl3x.bukkit.spawnereggcontrol.listener.BukkitListener;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnerEggControl extends JavaPlugin {
    @Override
    public void onEnable() {
        Config.reload(this);
        Lang.reload(this);

        getServer().getPluginManager().registerEvents(new BukkitListener(this), this);

        getCommand("spawnereggcontrol").setExecutor(new CmdSpawnerEggControl(this));
    }

    public boolean hasPermission(Player player, EntityType type) {
        if (player.hasPermission("spawnereggcontrol.all")) {
            return true;
        }
        return player.hasPermission("spawnereggcontrol." + type.getName());
    }
}
