package com.hv0905.modernRedstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRSLocker extends Block {

	public static final String BLOCK_ID = "rs_locker";

	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool CD = PropertyBool.create("cd");
	
	
	public BlockRSLocker() {
		super(Material.REDSTONE_LIGHT);
		this.setRegistryName(ModernRedstoneMain.MODID, BLOCK_ID);
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + BLOCK_ID);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false).withProperty(FACING, EnumFacing.NORTH).withProperty(CD, false));

	}

	// 当一个方块被放置时，会调用本方法
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.myupdateStatus(worldIn, pos, state);
	}

	// 更新
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.myupdateStatus(worldIn, pos, state);
	}

	// 更新
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.setBlockState(pos, state.withProperty(CD, false));
	}

	private void myupdateStatus(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote)
			return;
		EnumFacing facingA = state.getValue(FACING);
		EnumFacing facingB = state.getValue(FACING).getOpposite();
		BlockPos posA = pos.offset(facingA);
		BlockPos posB = pos.offset(facingB);
		System.out.println("pos:" + pos.getX() + "-" + pos.getY() + "-" + pos.getZ());
		System.out.println("power:" + state.getValue(POWERED).toString());
		System.out.println("powerB:" + worldIn.getRedstonePower(posB, facingB));
		System.out.println("powerN:" + worldIn.getRedstonePower(posA, facingA));
		if (!state.getValue(CD)) {
			if (state.getValue(POWERED)) {// A:on B:off
				if (worldIn.getRedstonePower(posB, facingB) > 0) {
					System.out.println("!!!POWER changed to false!!!");
					worldIn.setBlockState(pos, state.withProperty(POWERED, false).withProperty(CD, true));
					this.notifyNeighbors(worldIn, pos, (EnumFacing) state.getValue(FACING).getOpposite());
					worldIn.scheduleUpdate(pos, this, 5);
				}
			} else {// B:on A:off
				if (worldIn.getRedstonePower(posA, facingA) > 0) {
					System.out.println("!!!POWER changed to true!!!");
					worldIn.setBlockState(pos, state.withProperty(POWERED, true).withProperty(CD, true));
					this.notifyNeighbors(worldIn, pos, (EnumFacing) state.getValue(FACING));
					worldIn.scheduleUpdate(pos, this, 1);
				}
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);// 调整方向
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWERED, FACING, CD });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(POWERED) ? 6 : 0) + (state.getValue(FACING).getIndex());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState ibs;
		if (meta < 6) {
			ibs = this.getDefaultState().withProperty(POWERED, false);
		} else {
			ibs = this.getDefaultState().withProperty(POWERED, true);
			meta -= 6;
		}
		return ibs.withProperty(FACING, EnumFacing.getFront(meta)).withProperty(CD, false);
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return false;
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		// System.out.println("QUEUE:" + pos.getX() + ":" + pos.getY() + ":" +
		// pos.getZ());
		// System.out.println("power:" + blockState.getValue(POWERED));
		// System.out.println("SIDE:" + side.toString());
		if (blockState.getValue(POWERED)) {
			// A:ON B:OFF
			if (side == blockState.getValue(FACING).getOpposite()) {
				return 15;
			}
		} else {
			// A:off B:on
			 if (side == blockState.getValue(FACING)) {
			 return 15;
			 }
		}
		return 0;
	}

	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return getWeakPower(blockState, blockAccess, pos, side);
	}
	
	

	private void notifyNeighbors(World worldIn, BlockPos pos, EnumFacing facing) {
		worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this, true);
		worldIn.notifyNeighborsOfStateChange(pos, this, false);
	}

}
