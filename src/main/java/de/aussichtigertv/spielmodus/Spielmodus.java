package de.aussichtigertv.spielmodus;

import de.aussichtigertv.spielmodus.countdown.LobbyCoutdown;
import de.aussichtigertv.spielmodus.listener.CancelListener;
import de.aussichtigertv.spielmodus.listener.ChatListener;
import de.aussichtigertv.spielmodus.listener.ConnectionListener;
import de.aussichtigertv.spielmodus.util.GameState;
import de.aussichtigertv.spielmodus.util.Method;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Spielmodus extends JavaPlugin {

    private Method method;

    private static GameState gameState;
    private String prefix;
    private static Spielmodus instance;

    private File file;
    private YamlConfiguration config;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        prefix = "§7[§dSpiel§7]";
        gameState = GameState.LOBBY;
        method = new Method();

        loadConfig();


        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new CancelListener(), this);

        LobbyCoutdown lobbyCoutdown = new LobbyCoutdown();
        lobbyCoutdown.startCountdown(config.getInt("lobbycountdown"));

    }

    private void loadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();

        file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        config.options().copyDefaults(true);
        config.addDefault("prefix","&7[&dSpiel&7]" );
        config.addDefault("lobbycoutdown",60);
        config.addDefault("maps", new ArrayList<String>());

        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Spielmodus getInstance() {
        return instance;

    }
    public Method getMethod() {
        return method;
    }

    public String getPrefix() {
        return prefix;
    }

    public static GameState getGameState() { return gameState; }

    public static void setGameState(GameState gameState){
        Spielmodus.gameState = gameState;
    }

    @Override
    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
