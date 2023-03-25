package de.aussichtigertv.spielmodus.manager.gamemap;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class VoidGenerator
        extends ChunkGenerator {

    public byte[] generate( World w, Random rand, int x, int z ) {
        byte[] result = new byte[32768];
        if ( x == 0 && z == 0 ) {
            result[this.xyz( 0, 64, 0 )] = (byte) Material.BEDROCK.getId();
            result[this.xyz( 1, 64, 0 )] = (byte) Material.BEDROCK.getId();
            result[this.xyz( 0, 64, 1 )] = (byte) Material.BEDROCK.getId();
        }
        return result;
    }

    private Integer xyz( int x, int y, int z ) {
        return ( x * 16 + z ) * 128 + y;
    }
}
