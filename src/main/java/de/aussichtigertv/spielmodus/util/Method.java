package de.aussichtigertv.spielmodus.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Method {
    public static void setFooter(Player player) {
        player.setPlayerListFooter("\n§bDiscord §8- §7/discord \n §6Website §8- §7/website\n");
    }

    public static void setHeader(Player player) {
        player.setPlayerListHeader("\n §x§f§b§7§8§0§0S§x§f§7§8§7§0§0p§x§f§4§9§6§0§0i§x§f§0§a§4§0§0e§x§e§c§b§3§0§0l§x§e§9§c§2§0§0m§x§e§5§d§1§0§0o§x§e§1§d§f§0§0d§x§d§e§e§e§0§0u§x§d§a§f§d§0§0s §8▪  §8«\n\n §b" + Bukkit.getServer().getOnlinePlayers().size() + " §8x §7Spieler \n");
    }

    public static void setHeaderAndFooter(Player player) {
        setFooter(player);
        setHeader(player);

    }

    public String saveLocationToString(Location location) {
        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";"
                + location.getYaw() + ";" + location.getPitch();
    }

    public Location locationFromString(String locationString) {
    if(!locationString.contains(";")) {
        return null;
    }
    String[] arrayString = locationString.split(";");
    if(arrayString.length != 6) {
    return null;

    }
    World world = Bukkit.getWorld(arrayString[0]);

    double x = Double.parseDouble(arrayString[1]);
    double y = Double.parseDouble(arrayString[2]);
    double z = Double.parseDouble(arrayString[3]);

    float yaw = Float.parseFloat(arrayString[4]);
    float pitch = Float.parseFloat(arrayString[5]);

    return new Location(world , x , y , z , yaw , pitch);

    }
}

