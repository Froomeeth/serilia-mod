package serilia.content;

import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.gen.Sounds;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import serilia.world.blocks.Misc.GeneratorCore;

import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.*;

public class AndarvosBlocks {
    public static Block
    //Misc
    coreSprout;
    public static void load() {
        //MISC----------------------------------------------------------------------------------------------------------
        coreSprout = new GeneratorCore("core-sprout"){{
            requirements(effect, ItemStack.with(Items.graphite, 1000));
            alwaysUnlocked = true;

            scaledHealth = 225;
            armor = 5f;
            size = 4;
            itemCapacity = 2500;

            isFirstTier = true;
            unitType = UnitTypes.emanate;
            thrusterLength = 34/4f;

            unitCapModifier = 5;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.25f;

            outputsPower = true;
            consumesPower = false;
            powerProduction = 500/60f;
        }};
    }
}
