package com.hv0905.modernRedstone;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSilicon extends Block {
	//静态方块
	public static final String BLOCK_ID="silicon_block";
	
	
	public BlockSilicon(){
		super(Material.IRON);
		setHardness(2.0f);
		setResistance(10f);
		setLightLevel(0.625f);
		setHarvestLevel("pickaxe",1);
		setSoundType(SoundType.STONE);
		//registry
		setUnlocalizedName(ModernRedstoneMain.MODID + "." + BLOCK_ID);
		setRegistryName(ModernRedstoneMain.MODID,BLOCK_ID);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
	}

}
