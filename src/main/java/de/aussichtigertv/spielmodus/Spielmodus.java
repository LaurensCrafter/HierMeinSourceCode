package de.aussichtigertv.spielmodus;

import de.aussichtigertv.spielmodus.command.SetupCommand;
import de.aussichtigertv.spielmodus.command.StartCommand;
import de.aussichtigertv.spielmodus.countdown.LobbyCountdown;
import de.aussichtigertv.spielmodus.listener.CancelListener;
import de.aussichtigertv.spielmodus.listener.ChatListener;
import de.aussichtigertv.spielmodus.listener.ConnectionListener;
import de.aussichtigertv.spielmodus.manager.gamemap.GameMapManager;
import de.aussichtigertv.spielmodus.util.GameState;
import de.aussichtigertv.spielmodus.util.Method;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Spielmodus extends JavaPlugin {

    private Method method;

    private static GameState gameState;
    private String prefix;
    private static Spielmodus instance;

    private File file;
    private YamlConfiguration config;

    private LobbyCountdown lobbyCountdown;
    private GameMapManager gameMapManager;


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("———————————————————————————————————");
        getServer().getConsoleSender().sendMessage("#           SpielmodusV1           #");
        getServer().getConsoleSender().sendMessage("#            Loading...           #");
        getServer().getConsoleSender().sendMessage("———————————————————————————————————");

        // Plugin startup logic
        instance = this;
        prefix = "§7[§x§f§b§7§8§0§0S§x§f§7§8§7§0§0p§x§f§4§9§6§0§0i§x§f§0§a§4§0§0e§x§e§c§b§3§0§0l§x§e§9§c§2§0§0m§x§e§5§d§1§0§0o§x§e§1§d§f§0§0d§x§d§e§e§e§0§0u§x§d§a§f§d§0§0s§7]";
        gameState = GameState.LOBBY;
        method = new Method();

        loadConfig();
        gameMapManager = new GameMapManager();
        gameMapManager.loadMaps();


        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new CancelListener(), this);

        getCommand("start").setExecutor(new StartCommand());
        getCommand("setup").setExecutor(new SetupCommand());


        lobbyCountdown = new LobbyCountdown();
        lobbyCountdown.startCountdown(60);

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
        config.addDefault("prefix","§7[§x§f§b§7§8§0§0S§x§f§7§8§7§0§0p§x§f§4§9§6§0§0i§x§f§0§a§4§0§0e§x§e§c§b§3§0§0l§x§e§9§c§2§0§0m§x§e§5§d§1§0§0o§x§e§1§d§f§0§0d§x§d§e§e§e§0§0u§x§d§a§f§d§0§0s§7]" );
        config.addDefault("lobbycoutdown",60);
        config.addDefault("minPlayers", 2);
        config.addDefault("maxPlayers", 12);
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

    public GameMapManager getGameMapManager() {
        return gameMapManager;
    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}

