package serilia.content;

import arc.graphics.Color;
import arc.struct.ObjectSet;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.production.BurstDrill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawLiquidTile;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.meta.BuildVisibility;
import serilia.world.blocks.Misc.GeneratorCore;
import serilia.world.blocks.Transport.DuctNode;
import serilia.world.blocks.Transport.HeavyDuct;

import static mindustry.content.Items.*;
import static serilia.content.SeriliaResources.*;
import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.*;

public class AndarvosBlocks {
    public static Block
            //Turrets
            //Production
            methaneExtractor, combustionDrill,
            //Distribution
            heavyDuct, ductNode, ductJunction, ductTower,
            //Liquids
            aqueduct,
            //Power
            //Defense
            //Crafting
            electricFoundry,
            //Units
            //Misc
            coreSprout, coreBurgeon;
            //Logic
    public static void load() {
        //TURRETS-------------------------------------------------------------------------------------------------------
        //PRODUCTION----------------------------------------------------------------------------------------------------
        methaneExtractor = new GenericCrafter("methane-extractor"){{
            requirements(production, ItemStack.with(remnants, 50));
            scaledHealth = 140;
            size = 3;
            buildCostMultiplier = 5;

            hasPower = true;
            consumePower(180/60f);

            hasLiquids = true;
            liquidCapacity = 100;
            outputLiquid = new LiquidStack(methane, 50/60f);

            craftTime = 30;
            researchCost = with(remnants, 50);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(),
                    new DrawDefault()
            );
        }};
        combustionDrill = new BurstDrill("combustion-drill"){{
            requirements(production, ItemStack.with(remnants, 20));
            scaledHealth = 120;
            size = 2;
            buildCostMultiplier = 3;

            liquidCapacity = 10;
            itemCapacity = 20;

            hasPower = false;
            consumeLiquids(LiquidStack.with(methane, 5/60f));

            tier = 1;
            drillTime = 500;

            drillEffect = Fx.blastExplosion;
            shake = 0.5f;
            arrowOffset = 0;
            arrowSpacing = 0;
            arrows = 1;

            researchCost = with(remnants, 20);
            buildVisibility = BuildVisibility.shown;
        }};
        //DISTRIBUTION--------------------------------------------------------------------------------------------------
        heavyDuct = new HeavyDuct("heavy-duct"){{
            requirements(distribution, ItemStack.with(remnants, 1));
            health = 100;
            size = 1;
            speed = 6f;

            researchCost = with(remnants, 5);
            armored = true;
        }};
        ductNode = new DuctNode("duct-node"){{
            requirements(distribution, with(remnants, 3));
            health = 100;
            size = 1;
            speed = 6f;
            buildCostMultiplier = 3f;

            researchCost = with(remnants, 15);
        }};
        ductJunction = new Junction("duct-junction"){{
            requirements(distribution, with(remnants, 3));
            health = 100;
            size = 1;
            speed = 6f;
            buildCostMultiplier = 3f;

            researchCost = with(remnants, 15);
        }};
        ductTower = new DuctBridge("duct-tower"){{
            requirements(distribution, with(remnants, 10, graphite, 10));

            health = 120;
            size = 1;
            speed = 10;
            range = 10;
            buildCostMultiplier = 2f;
            researchCost = with(remnants, 25, graphite, 25);
        }};
        //LIQUIDS-------------------------------------------------------------------------------------------------------
        aqueduct = new Conduit("water-duct"){{
            requirements(liquid, with(remnants, 1));
            health = 100;
            size = 1;

            liquidCapacity = 20f;
            explosivenessScale = flammabilityScale = 10f/20f;
            botColor = Color.valueOf("ffffff");
            researchCost = with(remnants, 10);
            underBullets = true;
        }};
        //POWER---------------------------------------------------------------------------------------------------------
        //DEFENSE-------------------------------------------------------------------------------------------------------
        //CRAFTING------------------------------------------------------------------------------------------------------
        //UNITS---------------------------------------------------------------------------------------------------------
        //MISC----------------------------------------------------------------------------------------------------------
        coreSprout = new GeneratorCore("core-sprout"){{
            requirements(effect, with(remnants, 1500, graphite, 1000));
            alwaysUnlocked = true;

            scaledHealth = 225;
            armor = 5f;
            size = 4;
            itemCapacity = 3000;

            isFirstTier = true;
            unitType = UnitTypes.incite;
            thrusterLength = 34/4f;

            unitCapModifier = 10;
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.25f;

            outputsPower = true;
            consumesPower = false;
            powerProduction = 500/60f;
        }};
        coreBurgeon = new GeneratorCore("core-burgeon"){{
            requirements(effect, ItemStack.with(remnants, 3000, graphite, 2000));

            scaledHealth = 225;
            armor = 5f;
            size = 5;
            itemCapacity = 7000;

            isFirstTier = false;
            unitType = UnitTypes.emanate;
            thrusterLength = 34/4f;

            unitCapModifier = 10;
            ambientSound = Sounds.loopElectricHum;
            ambientSoundVolume = 0.25f;

            outputsPower = true;
            consumesPower = false;
            powerProduction = 1500/60f;
        }};
        //LOGIC---------------------------------------------------------------------------------------------------------
    }
}
