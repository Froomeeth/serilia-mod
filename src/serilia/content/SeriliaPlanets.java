package serilia.content;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.SunMesh;
import mindustry.type.Planet;
import mindustry.world.meta.Env;
import serilia.maps.planets.AndarvosPlanetGenerator;

import static serilia.content.AndarvosBlocks.*;

public class SeriliaPlanets extends Planets {
    public static Planet
            serilia,
            orbit,
            andarvos;
    public static void load(){
        serilia = new Planet("serilia", null, 5f){{
            bloom = true;
            accessible = false;
            meshLoader = () -> new SunMesh(
                    this, 6,
                    5, 0.3, 1.7, 1.2, 1,
                    1.1f,
                    Color.valueOf("ff383f"),
                    Color.valueOf("ff4d38"),
                    Color.valueOf("ff844c"),
                    Color.valueOf("ff9359"),
                    Color.valueOf("ffaf71"),
                    Color.valueOf("f4c98e")
            );
            orbitSpacing = 16f;
        }};
        andarvos = new Planet("andarvos", serilia, 1f, 3){{
            bloom = false;
            iconColor = Color.valueOf("91b675");
            alwaysUnlocked = true;
            defaultEnv = Env.terrestrial;
            defaultCore = coreSprout;
            prebuildBase = true;
            startSector = 191;
            sectorSeed = 2;
            allowWaves = true;
            generator = new AndarvosPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Color.valueOf("998ca2")).mul(0.9f).a(0.50f), 2, 0.45f, 0.9f, 0.33f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Color.valueOf("998ca2"), 0.55f).a(0.50f), 2, 0.45f, 1f, 0.33f)
            );

            landCloudColor = Color.valueOf("706f74");

            atmosphereColor = Color.valueOf("716579");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;

            lightDstFrom = 0.1f;
            lightDstTo = 0.1f;
            lightSrcFrom = 0.1f;
            lightSrcTo = 0.1f;
            lightColor = Color.valueOf("3a4d36");

            ruleSetter = r -> {
                r.waveTeam = Team.green;
                r.showSpawns = true;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };
            campaignRuleDefaults.showSpawns = true;
        }};
    }
}
