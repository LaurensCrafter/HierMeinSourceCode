package de.aussichtigertv.spielmodus.countdown;

import de.aussichtigertv.spielmodus.Spielmodus;
import org.bukkit.Bukkit;

public class RestartingCoutdown extends Countdown{

    @Override
    public void onStart() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §cDas Spiel ist nun beendet. Server startet jetzt neu...");


    }

    @Override
    public void onEnd() {
        Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §cDer Server startet jetzt neu!");
        Bukkit.getServer().shutdown();

    }

    @Override
    public void run() {
        if(getRemainingSeconds() == 15 || getRemainingSeconds() == 5 ||
                getRemainingSeconds() == 3 || getRemainingSeconds() == 2 || getRemainingSeconds() == 1) {
            Bukkit.broadcastMessage(Spielmodus.getInstance().getPrefix() + " §cDer Server startet in §e " + getRemainingSeconds() + " Sekunden §eneu!");
        }

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setLevel(getRemainingSeconds());
            player.setExp((float) getRemainingSeconds() / (float) getStartedSeconds());
        });
    }
}
