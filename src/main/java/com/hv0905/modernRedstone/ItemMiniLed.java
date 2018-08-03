package com.hv0905.modernRedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMiniLed extends Item {
	
	public static final String ITEM_ID="mini_led";
	
	public ItemMiniLed(){
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setRegistryName(ModernRedstoneMain.MODID, ITEM_ID);
		this.setUnlocalizedName(ModernRedstoneMain.MODID+"."+ITEM_ID);
	}

}
