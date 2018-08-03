package com.hv0905.modernRedstone;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;

public final class Helper {
	
	public static int getMax(int... numbers){
		if(numbers.length == 0) return 0;
		int max = numbers[0];
		
		for(int i = 1;i < numbers.length;i++) {
			if(numbers[i] > max) 
				max = numbers[i];
			
		}
		return max;
	}
	
	public static int getBlockPower(World world,BlockPos pos)
	{
		
		int down = world.getRedstonePower(pos.down(), EnumFacing.DOWN);
		int up = world.getRedstonePower(pos.up(), EnumFacing.UP);
		int north = world.getRedstonePower(pos.north(), EnumFacing.NORTH);
		int south = world.getRedstonePower(pos.south(), EnumFacing.SOUTH);
		int west = world.getRedstonePower(pos.west(), EnumFacing.WEST);
		int east = world.getRedstonePower(pos.east(), EnumFacing.EAST);
		int strong  = world.getStrongPower(pos);
		
		
		return getMax(down,up,north,south,west,east,strong);
		
	}
	
	public static BlockPos getFacPos(BlockPos pos,EnumFacing facing,int count) {
		switch(facing) {
		case NORTH:
			return pos.north(count);
		case SOUTH:
			return pos.south(count);
		case WEST:
			return pos.west(count);
		case EAST:
			return pos.east(count);
		case UP:
			return pos.up(count);
		case DOWN:
			return pos.down(count);
		default:
			return pos;
		}
		
		
	}
	
	private Helper(){}
}
