/**
 * Registers the mod blocks
 * SIGNUM
 * MIT License
 * Lana
 * */
package za.lana.signum.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import za.lana.signum.Signum;
import za.lana.signum.block.custom.AssemblyStationBlock;
import za.lana.signum.block.custom.ModOre.ElementZeroOreBlock;
import za.lana.signum.block.custom.RazorWireBlock;
import za.lana.signum.block.custom.SkyForgeBlock;
import za.lana.signum.block.custom.crystal.BuddingTiberiumBlock;
import za.lana.signum.block.custom.crystal.TiberiumBlock;
import za.lana.signum.block.custom.crystal.TiberiumClusterBlock;
import za.lana.signum.block.custom.props.BlightBlock;
import za.lana.signum.item.ModItemGroups;

//obsidian hardness (50.0f, 1200.0f)
//iron hardness (5.0f, 6.0f)
//steel hardness (unknown)
//the names here needs to match the json files

public class ModBlocks {

    public static final Block MANGANESE_ORE = registerBlock("manganese_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.STONE)
                    .strength(10.0f, 15.0f).requiresTool(), UniformIntProvider.create(2, 5)));
    public static final Block MOISSANITE_ORE = registerBlock("moissanite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.STONE)
                    .strength(10.0f, 15.0f).requiresTool(), UniformIntProvider.create(3, 6)));
    public static final Block ELEMENT_ZERO_ORE = registerBlock("element_zero_ore",
            new ElementZeroOreBlock(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.STONE).strength(4.0f).requiresTool()
                    .luminance(state -> state.get (ElementZeroOreBlock.LIT)? 6 : 0)));
    public static final Block DEEPSLATE_MANGANESE_ORE = registerBlock("deepslate_manganese_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).sounds(BlockSoundGroup.DEEPSLATE)
                    .strength(15.0f, 20.0f).requiresTool(), UniformIntProvider.create(3, 6)));
    public static final Block DEEPSLATE_MOISSANITE_ORE = registerBlock("deepslate_moissanite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).sounds(BlockSoundGroup.DEEPSLATE)
                    .strength(15.0f, 20.0f).requiresTool(), UniformIntProvider.create(3, 9)));
    public static final Block DEEPSLATE_ELEMENT_ZERO_ORE = registerBlock("deepslate_element_zero_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).sounds(BlockSoundGroup.DEEPSLATE)
                    .strength(15.0f, 20.0f).requiresTool(), UniformIntProvider.create(3, 7)));

    public static final Block NETHERRACK_MANGANESE_ORE = registerBlock("netherrack_manganese_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).sounds(BlockSoundGroup.NETHER_ORE)
                    .strength(5.0f, 15.0f).requiresTool(), UniformIntProvider.create(3, 6)));
    public static final Block ENDSTONE_MANGANESE_ORE = registerBlock("endstone_manganese_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).sounds(BlockSoundGroup.NETHER_ORE)
                    .strength(10.0f, 15.0f).requiresTool(), UniformIntProvider.create(3, 6)));
    public static final Block MANGANESE_BLOCK = registerBlock("manganese_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE)
                    .strength(30.0f, 300.0f)));
    public static final Block MOISSANITE_BLOCK = registerBlock("moissanite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .strength(10.0f, 600.0f)));

    public static final Block TIBERIUM_BLOCK = registerBlock("tiberium_block",
            new TiberiumBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LIME)
                    .strength(30.0f, 100.0f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK).requiresTool()));
    public static final Block BLIGHT_BLOCK = registerBlock("blight_block",
            new BlightBlock(FabricBlockSettings.copyOf(Blocks.DIRT).mapColor(MapColor.LIME)
                    .strength(10.0f, 100.0f)
                    .sounds(BlockSoundGroup.ROOTED_DIRT).requiresTool()));
    public static final Block BUDDING_TIBERIUM = registerBlock("budding_tiberium", new BuddingTiberiumBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST).mapColor(MapColor.LIME).ticksRandomly().strength(1.5f).sounds(BlockSoundGroup.AMETHYST_BLOCK).luminance(state -> 3).requiresTool().pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block TIBERIUM_CLUSTER = registerBlock("tiberium_cluster", new TiberiumClusterBlock(7, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.LIME).solid().nonOpaque().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance(state -> 7).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LARGE_TIBERIUM_BUD = registerBlock("large_tiberium_bud", new TiberiumClusterBlock(5, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD).solid().luminance(state -> 5).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block MEDIUM_TIBERIUM_BUD = registerBlock("medium_tiberium_bud", new TiberiumClusterBlock(4, 3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER).sounds(BlockSoundGroup.MEDIUM_AMETHYST_BUD).solid().luminance(state -> 3).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block SMALL_TIBERIUM_BUD = registerBlock("small_tiberium_bud", new TiberiumClusterBlock(3, 4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER).sounds(BlockSoundGroup.SMALL_AMETHYST_BUD).solid().luminance(state -> 1).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block RAZORWIRE_BLOCK = registerBlock("razorwire_block",
            new RazorWireBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(8.0f,100.0f)));
    public static final Block SKYFORGE = registerBlock("skyforge",
            new SkyForgeBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(5.0f,200.0f).nonOpaque()));
    public static final Block ASSEMBLY_STATION_BLOCK = registerBlock("assembly_station_block",
            new AssemblyStationBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(5.0f,200.0f).nonOpaque()));





    //registering blocks
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Signum.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Signum.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Signum.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Signum.LOGGER.info("Registering ModBlocks for " + Signum.MOD_ID);
    }
}
