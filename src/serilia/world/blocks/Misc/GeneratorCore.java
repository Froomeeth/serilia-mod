package serilia.world.blocks.Misc;

import arc.Core;
import arc.func.Func;
import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Strings;
import arc.util.Time;
import mindustry.core.UI;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class GeneratorCore extends CoreBlock{
    public float powerProduction = 0;
    public Stat generationType = Stat.basePowerGeneration;
    public Color[] glowColors = {Color.valueOf("00000000"), Color.coral, Color.valueOf("ff6161"), Color.pink, Color.acid, Color.sky};
    public TextureRegion glow;
    public float glowMag = 0.5f, glowScl = 10f;

    public GeneratorCore(String name){
        super(name);
        hasPower = true;
        outputsPower = true;
    }

    @Override
    public void load(){
        super.load();
        glow = Core.atlas.find(name + "-glow");
        uiIcon = fullIcon = editorIcon = Core.atlas.find(name + "-full");
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("poweroutput", (GeneratorCoreBuild entity) -> {
            return new Bar(() -> {
                return Core.bundle.format("bar.poweroutput", Strings.fixed(powerProduction * 60 + 0.0001f, 1));
            }, () -> {
                return Pal.powerBar;
            }, () -> {
                return 1f;
            });
        });
    }
    @Override
    public void setStats(){
        super.setStats();
        stats.add(generationType, powerProduction * 60.0f, StatUnit.powerSecond);
    }

    public class GeneratorCoreBuild extends CoreBuild{
        @Override
        public float getPowerProduction(){
            return powerProduction;
        }
        @Override
        public void draw(){
            super.draw();
            Drawf.additive(glow, team.id < 6 ? glowColors[team.id] : glowColors[1], 1f - glowMag + Mathf.absin(Time.time, glowScl, glowMag), x, y, 0f, Layer.blockAdditive);
        }
    }
}