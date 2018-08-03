package com.hv0905.modernRedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemComputingChip extends Item {
	
	public static final String ITEM_ID="computing_chip";
	
	public ItemComputingChip(){
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setRegistryName(ModernRedstoneMain.MODID,ITEM_ID);
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+ITEM_ID);
	}

}
