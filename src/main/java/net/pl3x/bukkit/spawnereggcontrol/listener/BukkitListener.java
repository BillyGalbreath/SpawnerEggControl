package net.pl3x.bukkit.spawnereggcontrol.listener;

import net.pl3x.bukkit.spawnereggcontrol.SpawnerEggControl;
import net.pl3x.bukkit.spawnereggcontrol.configuration.Lang;
import net.pl3x.purpur.event.PlayerSetSpawnerTypeWithEggEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BukkitListener implements Listener {
    private final SpawnerEggControl plugin;

    public BukkitListener(SpawnerEggControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSetSpawnerTypeWithEgg(PlayerSetSpawnerTypeWithEggEvent event) {
        if (!plugin.hasPermission(event.getPlayer(), event.getEntityType())) {
            Lang.send(event.getPlayer(), Lang.CANNOT_SET_TYPE);
            event.setCancelled(true);
        }
    }
}
