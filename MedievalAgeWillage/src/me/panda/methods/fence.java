package me.panda.methods;

import org.bukkit.Location;
import org.bukkit.Material;

public class fence {

	public static void createFence(Location mid, int radius){
		
		int minX = mid.getBlockX() - radius;
		int maxX = mid.getBlockX() + radius;
		int minZ = mid.getBlockZ() - radius;
		int maxZ = mid.getBlockZ() + radius;
		
		for(int i = 0 ; i < radius ; i++){
			if( i == 0){
			mid.getWorld().getHighestBlockAt(minX, minZ).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(maxX, maxZ).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(maxX, minZ).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(minX, maxZ).setType(Material.FENCE);
			
			mid.getWorld().getHighestBlockAt(mid.getBlockX(), minZ).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(mid.getBlockX(), maxZ).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(maxX, mid.getBlockZ()).setType(Material.FENCE);
			mid.getWorld().getHighestBlockAt(minX, mid.getBlockZ()).setType(Material.FENCE);
			}
			else{
				mid.getWorld().getHighestBlockAt(minX + i, minZ).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(maxX - i, maxZ).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(maxX - i, minZ).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(minX + i, maxZ).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(minX, minZ + i).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(maxX, maxZ - i).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(maxX, minZ + i).setType(Material.FENCE);
				mid.getWorld().getHighestBlockAt(minX, maxZ - i).setType(Material.FENCE);
			}
		}
		
	}
	
}
