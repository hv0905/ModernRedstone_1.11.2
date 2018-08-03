package com.hv0905.modernRedstone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLedLight extends Block {
	
	public static final String BLOCK_ID="led_light";
	//红石：15  analog：0.067 一格红石
	public static int redstonePower;
	
	public BlockLedLight(int redstonePower) {
		super(Material.REDSTONE_LIGHT);
		this.redstonePower = redstonePower;
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+this.BLOCK_ID+"_"+redstonePower);
		this.setRegistryName(ModernRedstoneMain.MODID,BLOCK_ID+"_"+redstonePower);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setHardness(1f);
		this.setResistance(6.0f);
		this.setLightLevel(redstonePower / 15f);
		this.setHarvestLevel("pickaxe", -1);
		this.setSoundType(SoundType.GLASS);
	}
	//方块事件
	
	//当一个方块被放置时，会调用本方法
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		//System.out.println("onBlockAdded");
		if(!worldIn.isRemote){
			int powerNow = Helper.getBlockPower(worldIn, pos);
			worldIn.setBlockState(pos, ModernRedstoneMain.blockLedLight[powerNow].getDefaultState(),2);
		}
    }
	
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
			int powerNow = Helper.getBlockPower(worldIn, pos);
			worldIn.setBlockState(pos, ModernRedstoneMain.blockLedLight[powerNow].getDefaultState(),2);
        }
    }

	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	if(!worldIn.isRemote){
			int powerNow = Helper.getBlockPower(worldIn, pos);
			worldIn.setBlockState(pos, ModernRedstoneMain.blockLedLight[powerNow].getDefaultState(),2);
    	}
    }

	//~方块事件
	
	//掉落物分配
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModernRedstoneMain.blockLedLight[0]);
    }
	
	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModernRedstoneMain.blockLedLight[0]);
    }
	
	@Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(ModernRedstoneMain.blockLedLight[0]);
    }
	
	//~掉落物分配
    
    
    
    
    
	
	

}
