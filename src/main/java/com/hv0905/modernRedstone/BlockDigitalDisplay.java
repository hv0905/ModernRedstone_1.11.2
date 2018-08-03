package com.hv0905.modernRedstone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;

public class BlockDigitalDisplay extends Block
{
	
	public static final String BLOCK_ID = "digital_display";
	
	public static final PropertyInteger NUMBER = PropertyInteger.create("number", 0, 15);
	

	public BlockDigitalDisplay() {
		super(Material.REDSTONE_LIGHT);
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+this.BLOCK_ID);
		this.setRegistryName(ModernRedstoneMain.MODID,BLOCK_ID);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setHardness(1f);
		this.setResistance(6.0f);
		this.setLightLevel(0.65f);
		this.setHarvestLevel("pickaxe", -1);
		this.setSoundType(SoundType.GLASS);
		this.setDefaultState(this.getBlockState().getBaseState().withProperty(NUMBER, 0));
	}
	
	//当一个方块被放置时，会调用本方法
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(!worldIn.isRemote){
        	int i= Helper.getBlockPower(worldIn, pos);
        	worldIn.setBlockState(pos, state.withProperty(NUMBER, i));
    	}
    }
    
    //更新
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
        	int i= Helper.getBlockPower(worldIn, pos);
        	worldIn.setBlockState(pos, state.withProperty(NUMBER, i));
        }
    }
    
    //更新
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
        	int i= Helper.getBlockPower(worldIn, pos);
        	worldIn.setBlockState(pos, state.withProperty(NUMBER, i));
        }
    }
    
    
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{NUMBER});
    }
	
	@Override
    public int getMetaFromState(IBlockState state)
    {
    	return state.getValue(NUMBER);
    }
    
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
    	return this.getDefaultState().withProperty(NUMBER, meta);
    }

}
