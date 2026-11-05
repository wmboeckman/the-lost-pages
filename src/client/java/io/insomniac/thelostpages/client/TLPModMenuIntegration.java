package io.insomniac.thelostpages.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class TLPModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Early return when missing cloth config api
        if (!FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return null;
        }
        // This links the Mod Menu button to your custom Cloth Config screen builder
        return TLPClothConfigScreen::createScreen;
    }
}
