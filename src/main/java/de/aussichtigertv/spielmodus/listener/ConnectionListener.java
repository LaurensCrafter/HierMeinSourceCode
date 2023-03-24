package de.aussichtigertv.spielmodus.listener;

import de.aussichtigertv.spielmodus.Spielmodus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);

        Spielmodus.getInstance().getMethod().setHeaderAndFooter(player); //sendet TAB

        Bukkit.getOnlinePlayers().forEach(players -> {
            players.sendMessage(Spielmodus.getInstance().getPrefix() + player.getDisplayName()+ "§7 hat das Spiel §abetreten§7.");
        });
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        Bukkit.getOnlinePlayers().forEach(players -> {
            players.sendMessage(Spielmodus.getInstance().getPrefix() + player.getDisplayName()+  "§7 hat das Spiel §cverlassen§7.");
        });
    }
}
