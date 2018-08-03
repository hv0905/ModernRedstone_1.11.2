package com.hv0905.modernRedstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRgbGlass extends Item {

	public static final String ITEM_ID = "rgb_glass";

	public ItemRgbGlass() {
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setUnlocalizedName(ModernRedstoneMain.MODID + "." + ITEM_ID);
		this.setRegistryName(ModernRedstoneMain.MODID, ITEM_ID);

	}
}
