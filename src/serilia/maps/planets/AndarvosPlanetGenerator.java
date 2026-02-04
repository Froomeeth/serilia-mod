package serilia.maps.planets;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import mindustry.content.Loadouts;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;

import static mindustry.content.Blocks.*;
import static serilia.content.SeriliaEnvBlocks.*;


public class AndarvosPlanetGenerator extends PlanetGenerator {
    public float heightScl = 0.9f, octaves = 10, persistence = 0.5f, heightPow = 2.5f, heightMult = 1.5f;
    public static int desertSeed = 3, desertOct = 5;
    public static float desertScl = 1f, desertMag = 0.1f;
    public static int veinSeed = 42, veinOct = 2;
    public static float veinScl = 1.2f, veinTresh = 0.3f;

    Block[] terrain = {deepwater,deepwater,deepwater, water, darksand, ferricStone, charr, stone, dacite};
    {
        baseSeed = 67;
        defaultLoadout = Loadouts.basicBastion;
    }
    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    @Override
    public void getColor(Vec3 position, Color out){
        Block block = getBlock(position);
        out.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public float getSizeScl(){
        return 2000 * 1.07f * 6f / 5f;
    }
    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    float rawTemp(Vec3 position){
        return position.dst(0, 0, 1)*2.2f - Simplex.noise3d(seed, 8, 0.54f, 1.4f, 10f + position.x, 10f + position.y, 10f + position.z) * 2.9f;
    }

    Block getBlock(Vec3 position){
        float px = position.x, py = position.y, pz = position.z;

        float ice = rawTemp(position);
        float height = rawHeight(position);

        height *= 1.2f;
        height = Mathf.clamp(height);

        Block result = terrain[Mathf.clamp((int)(height * terrain.length), 0, terrain.length - 1)];
        //Ferric stone vein thingies
        if (result == ferricStone){
            float vein = Ridged.noise3d(seed + veinSeed, px + 10f, py + 0f, pz + 0f, veinOct, veinScl);
            if(vein > veinTresh){
                if(ice < 0.9)
                    return basalt;
                else
                    return carbonStone;
            }
        }
        //The Moss
        if (ice < 0.7){
            if(height < 0.6 && (result != deepwater && result != water))
                return lush;
        }
        //trrar dessert underground
        if (ice > 0.8 +Math.abs(Ridged.noise3d(seed + desertSeed, px + 0f, py * 4f, pz + 0f,desertOct, desertScl)) * desertMag){
            if(height < 0.5)
                return sand;
            if (height < 0.6)
                return basalt;
            if (height < 0.4)
                return sandWater;
        }
        //peaks (like the g
        if (height > 0.8){
            if (ice < 0.9)
                return dacite;
        }
        return result;
    }
}