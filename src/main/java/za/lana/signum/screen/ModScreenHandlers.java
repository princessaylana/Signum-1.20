/**
 * SIGNUM
 * MIT License
 * Registers the mod screenhandlers for block entities
 * Lana
 * */

package za.lana.signum.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import za.lana.signum.Signum;

public class ModScreenHandlers {
    public static ScreenHandlerType<SkyForgeScreenHandler> SKYFORGE_SCREENHANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(Signum.MOD_ID, "skyforge"),
                    SkyForgeScreenHandler::new);

    /**
     *     public static final ScreenHandlerType<SkyForgeScreenHandler> SKYFORGE_SCREENHANDLER =
     *             Registry.register(Registries.SCREEN_HANDLER, new Identifier(Signum.MOD_ID, "skyforge_screenhandler"),
     *                     new ExtendedScreenHandlerType<>(SkyForgeScreenHandler::new));
     * */


    public static void registerScreenHandler() {
        Signum.LOGGER.info("Registering Screen Handlers for " + Signum.MOD_ID);
    }
}