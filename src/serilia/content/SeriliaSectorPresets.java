package serilia.content;

import mindustry.type.SectorPreset;

import static serilia.content.SeriliaPlanets.*;

public class SeriliaSectorPresets {
    public static SectorPreset
    testMap;
    public static void load(){
        testMap = new SectorPreset("test-map", andarvos, andarvos.startSector){{
            alwaysUnlocked = true;
            addStartingItems = true;
            captureWave = 5;
            difficulty = 1;
            overrideLaunchDefaults = true;
            noLighting = true;
            startWaveTimeMultiplier = 3;
        }};
    }
}
