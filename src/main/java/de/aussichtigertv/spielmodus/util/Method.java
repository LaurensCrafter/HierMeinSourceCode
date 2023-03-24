package de.aussichtigertv.spielmodus.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Method {
    public static void setFooter(Player player) {
        player.setPlayerListFooter("\n§bDiscord §8- §7/discord \n §6Website §8- §7/website\n");
    }

    public static void setHeader(Player player) {
        player.setPlayerListHeader("\n §l§f» §dSpielmodus §f§l« §8▪  §8«\n\n §b" + Bukkit.getServer().getOnlinePlayers().size() + " §8x §7Spieler \n");
    }

    public static void setHeaderAndFooter(Player player) {
        setFooter(player);
        setHeader(player);

    }

}

