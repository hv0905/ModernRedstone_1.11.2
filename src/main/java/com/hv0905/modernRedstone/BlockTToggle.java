package com.hv0905.modernRedstone;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTToggle extends Block {

	public static final String BLOCK_ID = "t_toggle";
	public static final PropertyBool POWER = PropertyBool.create("power");
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool TPOWER = PropertyBool.create("tpower");

	public BlockTToggle() {
		super(Material.ROCK);
		this.setRegistryName(ModernRedstoneMain.MODID, BLOCK_ID);
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + BLOCK_ID);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setDefaultState(
				this.blockState.getBaseState().withProperty(POWER, false).withProperty(FACING, EnumFacing.NORTH).withProperty(TPOWER, false));
		// 
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWER, FACING ,TPOWER});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(POWER) ? 6 : 0) + (state.getValue(FACING).getIndex());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState ibs;
		if (meta < 6) {
			ibs = this.getDefaultState().withProperty(POWER, false);
		} else {
			ibs = this.getDefaultState().withProperty(POWER, true);
			meta -= 6;
		}
		return ibs.withProperty(FACING, EnumFacing.getFront(meta)).withProperty(TPOWER, false);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);// 调整方向
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
		this.myupdateStatus(worldIn, pos, state);
	}

	private void myupdateStatus(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.isRemote)
			return;
		if(worldIn.getRedstonePower(pos.offset(state.getValue(FACING)), state.getValue(FACING)) > 0) {
			if(state.getValue(TPOWER)) {
				return;
			}else {
				worldIn.setBlockState(pos, state.withProperty(TPOWER, true).withProperty(POWER, !state.getValue(POWER)));
				
			}
		}else {
			if(state.getValue(TPOWER))
			worldIn.setBlockState(pos, state.withProperty(TPOWER, false));
		}
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		 System.out.println("QUEUE:" + pos.getX() + ":" + pos.getY() + ":" +
		 pos.getZ());
		 System.out.println("power:" + blockState.getValue(POWER));
		 System.out.println("SIDE:" + side.toString());
		if (blockState.getValue(POWER)) {
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
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return false;
	}
	
	

}
