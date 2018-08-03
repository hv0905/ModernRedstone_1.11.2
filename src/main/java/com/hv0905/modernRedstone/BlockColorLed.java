package com.hv0905.modernRedstone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockColorLed extends Block {
	
	public static final String BLOCK_ID="color_led";
	public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

	public BlockColorLed() {
		super(Material.REDSTONE_LIGHT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+this.BLOCK_ID);
		this.setRegistryName(ModernRedstoneMain.MODID,BLOCK_ID);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setHardness(1f);
		this.setResistance(6.0f);
		this.setLightLevel(0.65f);
		this.setHarvestLevel("pickaxe", -1);
		this.setSoundType(SoundType.GLASS);
	}
	
	
	
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{COLOR});
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }
    
    //当一个方块被放置时，会调用本方法
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(!worldIn.isRemote){
    		int power = Helper.getBlockPower(worldIn, pos);
    		worldIn.setBlockState(pos,state.withProperty(COLOR, EnumDyeColor.byMetadata(power)));
    	}
    }
    
    //更新
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
    		int power = Helper.getBlockPower(worldIn, pos);
    		worldIn.setBlockState(pos,state.withProperty(COLOR, EnumDyeColor.byMetadata(power)));
        }
    }
    
    //更新
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
    		int power = Helper.getBlockPower(worldIn, pos);
    		worldIn.setBlockState(pos,state.withProperty(COLOR, EnumDyeColor.byMetadata(power)));
        }
    }
    
    
	
	
}
