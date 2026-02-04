package serilia.world.blocks.Transport;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import arc.util.Eachable;
import mindustry.entities.units.BuildPlan;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.Duct;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class DuctNode extends Duct{
    public int chainLimit = 2;
    public TextureRegion topRegion;

    public DuctNode(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(name + "-top");
        uiIcon = fullIcon = Core.atlas.find(name + "-full");
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.color(Pal.accent);
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.color();
    }

    @Override
    public Block getReplacement(BuildPlan req, Seq<BuildPlan> plans){
        return this;
    }

    public void setStats(){
        super.setStats();
        stats.add(Stat.maxConsecutive, 2, StatUnit.none);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
    }

    public class DuctNodeBuild extends DuctBuild{
        public int chainCount, lastChainCount;
        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.color(lastChainCount >= chainLimit ? Pal.remove : Pal.accent);
            Draw.rect(topRegion, x, y, rotdeg());
            Draw.color();
        }

        @Override
        public void update(){
            if (next != null && next instanceof DuctNodeBuild duct){
                duct.chainCount = chainCount + 1;
            }

            if(chainCount < chainLimit){
                super.update();
            }
            lastChainCount = chainCount;
            chainCount = 0;
        }
    }
}
