package com.hv0905.modernRedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIoChip extends Item {
	
	public static final String ITEM_ID="io_chip";
	
	public ItemIoChip(){
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + ITEM_ID);
		this.setRegistryName(ModernRedstoneMain.MODID, ITEM_ID);
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	

}
