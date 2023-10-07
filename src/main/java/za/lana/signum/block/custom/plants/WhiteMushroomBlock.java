/**
 * SIGNUM
 * MIT License
 * Lana
 * 2023
 * */
package za.lana.signum.block.custom.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CherryLeavesBlock;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import za.lana.signum.particle.ModParticles;

import java.util.Map;

public class WhiteMushroomBlock
extends Block {
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    private static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES;

    public WhiteMushroomBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, true).with(EAST, true).with(SOUTH, true).with(WEST, true).with(UP, true).with(DOWN, true));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(DOWN, !blockView.getBlockState(blockPos.down()).isOf(this)).with(UP, !blockView.getBlockState(blockPos.up()).isOf(this)).with(NORTH, !blockView.getBlockState(blockPos.north()).isOf(this)).with(EAST, !blockView.getBlockState(blockPos.east()).isOf(this)).with(SOUTH, !blockView.getBlockState(blockPos.south()).isOf(this)).with(WEST, !blockView.getBlockState(blockPos.west()).isOf(this));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.isOf(this)) {
            return state.with(FACING_PROPERTIES.get(direction), false);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING_PROPERTIES.get(rotation.rotate(Direction.NORTH)), state.get(NORTH)).with(FACING_PROPERTIES.get(rotation.rotate(Direction.SOUTH)), state.get(SOUTH)).with(FACING_PROPERTIES.get(rotation.rotate(Direction.EAST)), state.get(EAST)).with(FACING_PROPERTIES.get(rotation.rotate(Direction.WEST)), state.get(WEST)).with(FACING_PROPERTIES.get(rotation.rotate(Direction.UP)), state.get(UP)).with(FACING_PROPERTIES.get(rotation.rotate(Direction.DOWN)), state.get(DOWN));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(FACING_PROPERTIES.get(mirror.apply(Direction.NORTH)), state.get(NORTH)).with(FACING_PROPERTIES.get(mirror.apply(Direction.SOUTH)), state.get(SOUTH)).with(FACING_PROPERTIES.get(mirror.apply(Direction.EAST)), state.get(EAST)).with(FACING_PROPERTIES.get(mirror.apply(Direction.WEST)), state.get(WEST)).with(FACING_PROPERTIES.get(mirror.apply(Direction.UP)), state.get(UP)).with(FACING_PROPERTIES.get(mirror.apply(Direction.DOWN)), state.get(DOWN));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(10) != 0) {
            return;
        }
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (CherryLeavesBlock.isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
            return;
        }
        ParticleUtil.spawnParticle(world, pos, random, ModParticles.WHITE_SHROOM_PARTICLE);
    }
}

