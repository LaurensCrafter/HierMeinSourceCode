package de.aussichtigertv.spielmodus.manager.gamemap;

import com.google.common.collect.Lists;
import de.aussichtigertv.spielmodus.Spielmodus;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameMapManager {

    private GameMap currentGameMap;

    private List<GameMap> gameMaps = Lists.newArrayList();

    public void loadMaps() {
        File mapFile = new File("maps");
        if(!mapFile.exists()) {
            mapFile.mkdirs();
        }

        if(mapFile.listFiles() != null && mapFile.listFiles().length != 0) {
        for (File file : mapFile.listFiles()) {
            GameMap gameMap = new GameMap(file.getName(), new File(file, file.getName() + ".yml"));
            gameMaps.add(gameMap);
            System.out.println("Map geladen: " + gameMap.getName() + " von " + gameMap.getAuthor());
            }
        } else {
            File defaultConfig = new File("maps/default" , "default.yml");
            defaultConfig.getParentFile().mkdirs();
            try {
                defaultConfig.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(defaultConfig);
            yamlConfiguration.set("displayname" , "&eDefault");
            yamlConfiguration.set("author", "AussichtigerTV & GmBH");

            try {
                yamlConfiguration.save(defaultConfig);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameMap gameMap = new GameMap(defaultConfig.getParentFile().getName(), defaultConfig);
            gameMaps.add(gameMap);
        }
        System.out.println("[Spielmodus] " + "Es wurden " + gameMaps.size() + " Maps geladen!");
    }

    public List<GameMap> getGameMaps() {
        return gameMaps;
    }

    public GameMap getGameMapByName(String name) {
        for (GameMap gameMap : gameMaps) {
            if(gameMap.getName().equalsIgnoreCase(name)) {
                return gameMap;
            }

        }
        return null;
    }

    public GameMap getRandomGameMap() {
        return this.gameMaps.get(new Random().nextInt(this.gameMaps.size()));
    }

    public GameMap getCurrentGameMap() {
        return currentGameMap;
    }

    public void setCurrentGameMap(GameMap currentGameMap) {
        this.currentGameMap = currentGameMap;
    }
}
