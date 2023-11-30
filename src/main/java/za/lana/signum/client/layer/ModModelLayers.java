/**
 * SIGNUM
 * MIT License
 * Lana
 * 2023
 * */
package za.lana.signum.client.layer;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import za.lana.signum.Signum;

public class ModModelLayers {
    public static final EntityModelLayer AIRBALLOON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "airballoon"), "MainBody");
    public static final EntityModelLayer UNICORN =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "unicorn"), "mainbody");
    public static final EntityModelLayer PIDGEON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "pidgeon"), "mainBody");
    public static final EntityModelLayer ESPIDER =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "espider_entity"), "mainbody");
    public static final EntityModelLayer ESPIDER_SADDLE =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "espider_entity"), "saddle");

    public static final EntityModelLayer TTROOPER =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "ttrooper_entity"), "waist");
    public static final EntityModelLayer SSKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "sskeleton_entity"), "mainBody");

    public static final EntityModelLayer TIBSKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "tibskeleton_entity"), "mainBody");
    public static final EntityModelLayer ICESKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "iceskeleton_entity"), "mainBody");

    public static final EntityModelLayer FIRESKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "fireskeleton_entity"), "mainBody");
    public static final EntityModelLayer ENDERSKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "enderskeleton_entity"), "mainBody");
    public static final EntityModelLayer DARKSKELETON =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "darkskeleton_entity"), "mainBody");


    public static final EntityModelLayer TCOMMANDER =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "tcommander_entity"), "waist");
    public static final EntityModelLayer ELVE_ENTITY =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "elve_entity"), "waist");

    public static final EntityModelLayer TIBERIUM_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "tiberium_bolt"), "main");
    public static final EntityModelLayer ICE_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "ice_bolt"), "main");
    public static final EntityModelLayer TRANSMUTE_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "transmute_bolt"), "main");
    public static final EntityModelLayer FIRE_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "fire_bolt"), "main");
    public static final EntityModelLayer SHOCK_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "shock_bolt"), "main");
    public static final EntityModelLayer GRAVITY_BOLT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "gravity_bolt"), "main");
    public static final EntityModelLayer SPIDER_SPIT =
            new EntityModelLayer(new Identifier(Signum.MOD_ID, "spiderspit"), "main");


}

