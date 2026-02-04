package serilia.content;

import arc.graphics.Color;
import mindustry.type.Item;
import mindustry.type.Liquid;

public class SeriliaResources {
    public static Item
            remnants, steel;
    public static Liquid
            methane;
    public static Item[] andarvos = {remnants, steel};
    public static void load() {
        //Items---------------------------------------------------------------------------------------------------------
        remnants = new Item("remnants", Color.valueOf("595365")) {{
            hardness = 1;
            cost = 1f;
            alwaysUnlocked = true;
        }};
        steel = new Item("steel", Color.valueOf("c3f5ff")) {{
            hardness = 3;
            cost = 1f;
        }};
        //Fluids--------------------------------------------------------------------------------------------------------
        methane = new Liquid("methane", Color.valueOf("ea8878")) {{
            gas = true;
            explosiveness = 1;
            flammability = 3;
        }};
    }
}
