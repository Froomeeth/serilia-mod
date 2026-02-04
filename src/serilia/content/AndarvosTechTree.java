package serilia.content;

import mindustry.content.Blocks;

import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;
import static serilia.content.SeriliaResources.*;
import static serilia.content.AndarvosBlocks.*;
import static serilia.content.SeriliaSectorPresets.*;

public class AndarvosTechTree{
    public static void load(){
        SeriliaPlanets.andarvos.techTree = nodeRoot("serilia-andarvos", coreSprout, () -> {
            node(heavyDuct, () -> {
                node(ductJunction, () -> {
                    node(ductTower, () -> {
                    });
                });
                node(ductNode, () -> {
                });
            });
            node(coreBurgeon, () -> {
            });
            node(aqueduct, () -> {
            });
            node(methaneExtractor, () -> {
                node(combustionDrill, () -> {
                    node(Blocks.powerNode, () -> {
                    });
                });
            });
            node(testMap, () -> {
            });
            node(remnants, () -> {
                node(graphite, () -> {
                    node(lead, () -> {
                        node(copper, () -> {
                        });
                    });
                });
                node(methane, () -> {
                });
            });
        });
        SeriliaPlanets.andarvos.techTree.addPlanet(SeriliaPlanets.andarvos);
    }
}
