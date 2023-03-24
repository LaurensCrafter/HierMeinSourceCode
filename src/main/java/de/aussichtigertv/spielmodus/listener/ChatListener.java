package de.aussichtigertv.spielmodus.listener;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.util.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (Spielmodus.getGameState().equals(GameState.LOBBY)) {
            event.setFormat("§a%s§8: §7%s");

        } else {
            event.setFormat("§c%s§8: §7%s");
        }

    }
}
