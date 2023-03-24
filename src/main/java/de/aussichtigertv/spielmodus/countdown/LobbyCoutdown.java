package de.aussichtigertv.spielmodus.countdown;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.util.GameState;
import org.bukkit.Bukkit;

public class LobbyCoutdown extends Countdown {
    @Override
    public void onStart() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " Lobbycountdown wird gestart!");

    }

    @Override
    public void onEnd() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " Spiel Startet nun.");

        Spielmodus.setGameState(GameState.INGAME);

        RestartingCountdown restartingCountdown = new RestartingCountdown();
        restartingCountdown.startCountdown(15);

    }

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().size() >= 2) {

            if(getRemainingSeconds() == 60 || getRemainingSeconds() == 30 ||
                    getRemainingSeconds() == 10 || getRemainingSeconds() == 5 || getRemainingSeconds() == 4
                    || getRemainingSeconds() == 3 || getRemainingSeconds() == 2 || getRemainingSeconds() == 1 ) {
                Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §7Das Spiel beginnt in §e" + getRemainingSeconds() + " §7Sekunden...");
            }
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setLevel(getRemainingSeconds());
                player.setExp((float) getRemainingSeconds() / ((float) getStartedSeconds()));
            });



        } else {
            setRemainingSeconds(61);
        }
    }
}