package de.aussichtigertv.spielmodus.listener;

import de.aussichtigertv.spielmodus.Spielmodus;
import de.aussichtigertv.spielmodus.util.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CancelListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            if(event.getAction().equals(Action.PHYSICAL))
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if (Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(Spielmodus.getGameState().equals(GameState.LOBBY) || Spielmodus.getGameState().equals(GameState.RESTART)) {
            event.setCancelled(true);
        }
    }
}
