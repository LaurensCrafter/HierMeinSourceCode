package de.aussichtigertv.spielmodus.manager.gamemap;

import de.aussichtigertv.spielmodus.Spielmodus;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GameMap {

    private final String name;

    private File configFile;
    private YamlConfiguration config;

    private String displayname;
    private String author;

    public GameMap (String name, File configFile) {
        this.name = name;
        this.configFile  = configFile;
        this.loadConfig();
    }
    private void loadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        this.displayname = ChatColor.translateAlternateColorCodes('&', this.config.getString("displayname", "&eDisplayname"));
        this.author = this.config.getString("author", "AussichtigerTV GmBH");

    }

    public void importMap() {
        File parentFile = this.configFile.getParentFile();
        boolean success = parentFile.renameTo(new File(getName()));
        if(success) {
            this.configFile = new File(getName(), getName() + ".yml");
            this.config = YamlConfiguration.loadConfiguration(this.configFile);

            WorldCreator worldCreator = new WorldCreator(parentFile.getName());
            worldCreator.generator(new VoidGenerator());
            worldCreator.generateStructures(false);
            worldCreator.environment(World.Environment.NORMAL);
            worldCreator.createWorld();
            Bukkit.createWorld(worldCreator);
            System.out.println("[Spielmodus] Die Map " + getName() + " wurde erfolgreich geladen!");
        } else {
            System.out.println("[Spielmodus] Fehler beim laden der Map " + getName());
        }
    }

    public void setMapLocation(String key, Location location) {
        this.config.set("locations." + key.toLowerCase(), Spielmodus.getInstance().getMethod().saveLocationToString(location));
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getMapLocation(String key) {
        return Spielmodus.getInstance().getMethod().locationFromString(this.config.getString("locations" + key.toLowerCase()));
    }

    public String getName() {
        return name;
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getAuthor() {
        return author;
    }
}
