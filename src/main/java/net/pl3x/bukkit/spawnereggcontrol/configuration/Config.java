package net.pl3x.bukkit.spawnereggcontrol.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Config {
    public static String LANGUAGE_FILE = "lang-en.yml";

    private static void init(Plugin plugin) {
        LANGUAGE_FILE = getString("language-file", LANGUAGE_FILE);
    }

    public static void reload(Plugin plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException ignore) {
        } catch (InvalidConfigurationException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not load config.yml, please correct your syntax errors", ex);
            throw new RuntimeException(ex);
        }
        config.options().header("This is the configuration file for " + plugin.getName());
        config.options().copyDefaults(true);

        Config.init(plugin);

        try {
            config.save(configFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save " + configFile, ex);
        }
    }

    private static YamlConfiguration config;

    private static String getString(String path, String def) {
        config.addDefault(path, def);
        return config.getString(path, config.getString(path));
    }
}
