package com.hv0905.modernRedstone;

import net.minecraftforge.common.MinecraftForge;

public class EventsDelegateMC {
	public EventsDelegateMC(){
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	

}
