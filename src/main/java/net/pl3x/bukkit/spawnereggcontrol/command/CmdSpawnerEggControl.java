package net.pl3x.bukkit.spawnereggcontrol.command;

import net.pl3x.bukkit.spawnereggcontrol.SpawnerEggControl;
import net.pl3x.bukkit.spawnereggcontrol.configuration.Config;
import net.pl3x.bukkit.spawnereggcontrol.configuration.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.List;

public class CmdSpawnerEggControl implements TabExecutor {
    private final SpawnerEggControl plugin;

    public CmdSpawnerEggControl(SpawnerEggControl plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && "reload".startsWith(args[0].toLowerCase()) && sender.hasPermission("command.spawnereggcontrol")) {
            return Collections.singletonList("reload");
        }
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String response = "&d" + plugin.getName() + " v" + plugin.getDescription().getVersion();

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            Config.reload(plugin);
            Lang.reload(plugin);

            response += " reloaded";
        }

        Lang.send(sender, response);
        return true;
    }
}
