package com.hv0905.modernRedstone;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDelay extends Block {
	
	public static final String BLOCK_ID = "delay";

	public BlockDelay() {
		super(Material.REDSTONE_LIGHT);
		setHardness(2.0f);
		setResistance(10f);
		setLightLevel(0.625f);
		setHarvestLevel("pickaxe",1);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabs.REDSTONE);
		
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + BLOCK_ID);
		this.setRegistryName(ModernRedstoneMain.MODID, BLOCK_ID);
		
	}

}
