package com.hv0905.modernRedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTimeChip extends Item {
	
	public static final String ITEM_ID="time_chip";
	
	public ItemTimeChip(){
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + ITEM_ID);
		this.setRegistryName(ModernRedstoneMain.MODID,ITEM_ID);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
	}

}
