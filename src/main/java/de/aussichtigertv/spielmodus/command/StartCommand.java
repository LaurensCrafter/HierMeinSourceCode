package de.aussichtigertv.spielmodus.command;

import de.aussichtigertv.spielmodus.Spielmodus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

        if (cs.hasPermission("game.start")) {
            if(Bukkit.getOnlinePlayers().size() >= Spielmodus.getInstance().getConfig().getInt("minPlayers"))
            if(Spielmodus.getInstance().getLobbyCountdown().getRemainingSeconds() > 10) {
                Spielmodus.getInstance().getLobbyCountdown().setRemainingSeconds(10);
                cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §aDer Countdown wurde verkürzt!");

            } else {
                cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §cDu kannst den Start nicht mehr überspringen!");
            } else {
                cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §cEs sind nicht genug Spieler online, um den Server zu Starten!");

            }

        } else {
            cs.sendMessage(Spielmodus.getInstance().getPrefix() + " §cKeine Rechte!");
        }


        return true;
    }
}
