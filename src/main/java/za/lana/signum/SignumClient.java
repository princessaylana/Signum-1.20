/**
 * SIGNUM
 * This is the client entry point
 * MIT License
 * Lana
 * */

package za.lana.signum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import za.lana.signum.entity.ModEntities;


public class SignumClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.TOXICBALL, (context) ->
                new FlyingItemEntityRenderer(context));


    }
}