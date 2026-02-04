package serilia;

import mindustry.mod.*;
import serilia.content.*;
import serilia.gen.*;

public class SeriliaMain extends Mod{
    @Override
    public void loadContent(){
        EntityRegistry.register();
        SeriliaResources.load();
        SeriliaEnvBlocks.load();
        AndarvosBlocks.load();
        SeriliaPlanets.load();
        SeriliaSectorPresets.load();
        AndarvosTechTree.load();
    }
}