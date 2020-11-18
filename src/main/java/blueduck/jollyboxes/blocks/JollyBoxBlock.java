package blueduck.jollyboxes.blocks;

import blueduck.jollyboxes.registry.JollyBoxesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.state.properties.SlabType;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class JollyBoxBlock extends FallingBlock {

    public static final VoxelShape SMALL = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    public static final VoxelShape MEDIUM = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D);
    public static final VoxelShape LARGE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public JollyBoxBlock(Properties properties) {
        super(properties);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        }
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        if (worldIn instanceof ServerWorld) {
            ServerWorld worldServer = (ServerWorld) worldIn;
            ItemStack loot = new ItemStack(ForgeRegistries.ITEMS.getValue(this.getRegistryName()));
            if (!loot.isEmpty()) {
                this.giveItem(player, loot);
                return ActionResultType.SUCCESS;
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    private void giveItem(PlayerEntity player, @Nonnull ItemStack stack) {
        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
        } else if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).sendContainerToPlayer(player.container);
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (JollyBoxesBlocks.SMALL_JOLLY_BOX.get().getDefaultState().equals(state)) {
            return SMALL;
        } else if (JollyBoxesBlocks.MEDIUM_JOLLY_BOX.get().getDefaultState().equals(state)) {
            return MEDIUM;
        }
        return LARGE;
    }


}
