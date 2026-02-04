package serilia.content;

import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.Prop;
import mindustry.world.blocks.environment.StaticWall;
import serilia.world.draw.DrawSpores;

import static mindustry.content.Items.*;
import static serilia.content.SeriliaResources.*;

public class SeriliaEnvBlocks {
    public static Block
    oreDebris,
    lush, smoothBasalt, soilstone, soilstoneWall, soilstoneBoulder;
    public static void load(){
        //Ores----------------------------------------------------------------------------------------------------------
        oreDebris = new Floor("ore-remnants") {{
            variants = 4;
            itemDrop = remnants;
            drawEdgeIn = true;
        }};
        oreDebris = new OreBlock("ore-graphite") {{
            variants = 3;
            itemDrop = graphite;
        }};
        //Environment---------------------------------------------------------------------------------------------------
        lush = new Floor("lush", 4){{
            drawEdgeIn = true;
        }};
        smoothBasalt = new Floor("smooth-basalt", 6){{
            drawEdgeIn = true;
        }};
        soilstone = new Floor("soilstone", 3){{
            wall = soilstoneWall;
            drawEdgeIn = true;
        }};
        soilstoneWall = new StaticWall("soilstone-wall"){{
          variants = 3;
        }};
        soilstoneBoulder = new Prop("soilstone-boulder"){{
            variants = 2;
            soilstone.asFloor().decoration = this;
        }};

    }
}
