package de.aussichtigertv.spielmodus.countdown;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.util.GameState;
import org.bukkit.Bukkit;

public class RestartingCountdown extends Countdown {

    @Override
    public void onStart() {
        Spielmodus.setGameState(GameState.RESTART);
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §7Spiel ist §cbeendet§7. Der Server §cstartet jetzt neu§7.... ");


    }

    @Override
    public void onEnd() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §cDer Server startet neu!");
        Bukkit.getServer().shutdown();


    }

    @Override
    public void run() {
        if (getRemainingSeconds() == 15 || getRemainingSeconds() == 5 ||
                getRemainingSeconds() == 4 || getRemainingSeconds() == 3 ||
                getRemainingSeconds() == 2 || getRemainingSeconds() == 1) {
            Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §cDer Server startet in §e" + getRemainingSeconds() + " Sekunden neu!");


        }
    }
}
