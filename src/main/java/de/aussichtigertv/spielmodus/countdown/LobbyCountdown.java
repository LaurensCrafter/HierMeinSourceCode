package de.aussichtigertv.spielmodus.countdown;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.manager.gamemap.GameMap;
import de.aussichtigertv.spielmodus.manager.gamemap.GameMapManager;
import de.aussichtigertv.spielmodus.util.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.BitSet;

public class LobbyCountdown extends Countdown {
    @Override
    public void onStart() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " Lobbycountdown wird gestartet");
    }

    @Override
    public void onEnd() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §aDas Spiel startet nun...");
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle("§aSpiel" ,"§aStartet nun...", 10, 20, 10);

        });

        Spielmodus.setGameState(GameState.INGAME);

        Location location = Spielmodus.getInstance().getGameMapManager().getCurrentGameMap().getMapLocation("spawn");
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(location);
            player.sendMessage(Spielmodus.getInstance().getPrefix() + " §aDu wurdest Teleportiert.");
        });

        /**
        RestartingCoutdown restartingCoutdown = new RestartingCoutdown();
        restartingCoutdown.startCountdown(15);**/

    }

    @Override
    public void run() {
        if(Bukkit.getOnlinePlayers().size() >= Spielmodus.getInstance().getConfig().getInt("minPlayers")) {
            if (getStartedSeconds() <= getRemainingSeconds()) {
                setRemainingSeconds(getStartedSeconds());
            }


            if(getRemainingSeconds() == 60 || getRemainingSeconds() == 30 ||
            getRemainingSeconds() == 10 || getRemainingSeconds() == 5 || getRemainingSeconds() == 4 ||
            getRemainingSeconds() == 3 || getRemainingSeconds() == 2 || getRemainingSeconds() == 1) {
                Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §aDas Spiel beginnt in §e" + getRemainingSeconds() + " Sekunden...");
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle("§e" + getRemainingSeconds(),"§7Sekunden noch..", 10, 20, 10);
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 2 , 3);
                });
            }

            if(getRemainingSeconds() == 10) {
                GameMap gameMap = Spielmodus.getInstance().getGameMapManager().getRandomGameMap();
                Spielmodus.getInstance().getGameMapManager().setCurrentGameMap(gameMap);
                Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §aMap: §e" + gameMap.getDisplayname());
                Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §aErbauer: §e" + gameMap.getAuthor());
                gameMap.importMap();
            }


            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setLevel(getRemainingSeconds());
                player.setExp((float) getRemainingSeconds() / (float) getStartedSeconds());
            });

        } else {
            if(getRemainingSeconds() <= getStartedSeconds()) {
                setRemainingSeconds(getStartedSeconds() + 15);
                Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §7Es werden noch weiter Spieler benötigt (§e" + Bukkit.getOnlinePlayers().size() + "§7/" + Spielmodus.getInstance().getConfig().getInt("minPlayers") + "§7)");
            }


            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setLevel(getStartedSeconds());
                player.setExp((float) getStartedSeconds() / (float) getStartedSeconds());
            });
        }
    }
}
