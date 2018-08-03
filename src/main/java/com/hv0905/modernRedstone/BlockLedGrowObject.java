package com.hv0905.modernRedstone;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLedGrowObject extends Block {

	public static final String BLOCK_ID="ledgrow_obj";
	
	
	public BlockLedGrowObject(){
		super(Material.GLASS);
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+this.BLOCK_ID);
		this.setRegistryName(ModernRedstoneMain.MODID,BLOCK_ID);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setHardness(1f);
		this.setResistance(6.0f);
		this.setLightLevel(1.0f);
		this.setHarvestLevel("pickaxe", -1);
		this.setSoundType(SoundType.GLASS);
		
	}
	
	

}
