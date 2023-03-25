package de.aussichtigertv.spielmodus.command;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.manager.gamemap.GameMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetupCommand implements CommandExecutor {

    /**
     *  /setup import <mapname>
     *  /setup create <mapname> <displayname> <authors>
     * /setup set <mapname> <keylocation>
     */
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs instanceof Player) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("import")) {
                    GameMap gameMap = Spielmodus.getInstance().getGameMapManager().getGameMapByName(args[1]);
                    if (gameMap != null) {
                        gameMap.importMap();
                        ((Player) cs).teleport(Bukkit.getWorld(gameMap.getName()).getSpawnLocation());
                        cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §aMap erfolgreich Importiert!");
                    } else {
                        cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §cDiese Map existiert nicht!");
                    }
                }

            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    GameMap gameMap = Spielmodus.getInstance().getGameMapManager().getGameMapByName(args[1]);
                    if (gameMap != null) {
                        gameMap.setMapLocation(args[2], ((Player) cs).getLocation());
                        cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §aDie Location §e" + args[2] + " §awurde erfolgreich gesetzt!");
                    } else {
                        cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §cDiese Map existiert nicht!");
                    }
                }
            } else if (args.length >= 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    String name = args[1];
                    String displayname = args[2];
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        stringBuilder.append(args[i]);
                        if (i < (args.length - 1)) {
                            stringBuilder.append(" & ");
                        }
                    }
                    String authors = stringBuilder.toString();

                    File mapConfig = new File("maps/" + name , name +".yml");
                    mapConfig.getParentFile().mkdirs();
                    try {
                        mapConfig.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(mapConfig);
                    yamlConfiguration.set("displayname" , displayname);
                    yamlConfiguration.set("author", authors);

                    try {
                        yamlConfiguration.save(mapConfig);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    GameMap gameMap = new GameMap(mapConfig.getParentFile().getName(), mapConfig);
                    Spielmodus.getInstance().getGameMapManager().getGameMaps().add(gameMap);
                    cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §aDie Map §e" + name + " §awurde erfolgreich erstellt!");

                }
        }
    }

        return true;
    }
}

