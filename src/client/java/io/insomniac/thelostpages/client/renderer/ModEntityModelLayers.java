package io.insomniac.thelostpages.client.renderer;

import io.insomniac.thelostpages.TheLostPages;
import io.insomniac.thelostpages.client.model.BookTestModel;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModEntityModelLayers {
    public static final ModelLayerLocation CONJURED_BOOK = createMain("conjured_book");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CONJURED_BOOK, BookTestModel::getTexturedModelData);
    }
}
