package me.panda.methods;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.panda.objects.cost;
import me.panda.objects.message;
import me.panda.objects.waluta;
import me.panda.objects.whatcost;
import me.panda.objects.willage;
import me.panda.willage.main;

public class info {

	public static willage getPlayerWillage(Player p){
		
		String nick = p.getName();
		willage will = main.players.get(nick);
		return will;
	}
	
	public static boolean hasWillage(Player p){
		String nick = p.getName();
		if(main.players.containsKey(nick)){
			return true;
		}
		return false;
	}
	
	public static boolean isLeader(Player p){
		String nick = p.getName();
		willage will = main.players.get(nick);
		if(will.getLeader().equals(nick)){
			return true;
		}
		return false;
	}
	
	public static boolean isInWillageArea(Location loc){
		
		for(willage will : main.willages){
			if(will.getMiddle().getWorld().getName().equals(loc.getWorld().getName())){
				
				int lvl = will.getLvL();
				int radius = main.levels.get(lvl).getRadius();
				Location mid = will.getMiddle();
				
				if(loc.getBlockX() >= mid.getBlockX() - radius && loc.getBlockX() <= mid.getBlockX() + radius){
					if(loc.getBlockZ() >= mid.getBlockZ() - radius && loc.getBlockZ() <= mid.getBlockZ() + radius){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	
	public static boolean isInMainWillageArea(Player p){
		Location loc = p.getLocation();
		if(hasWillage(p)){
		willage will = info.getPlayerWillage(p);
			if(will.getMiddle().getWorld().getName().equals(loc.getWorld().getName())){
				
				int lvl = will.getLvL();
				int radius = main.levels.get(lvl).getRadius();
				Location mid = will.getMiddle();
				
				if(loc.getBlockX() >= mid.getBlockX() - radius && loc.getBlockX() <= mid.getBlockX() + radius){
					if(loc.getBlockZ() >= mid.getBlockZ() - radius && loc.getBlockZ() <= mid.getBlockZ() + radius){
						main.addLog("tru");
						return true;
					}
				}
			}	
			}
		
		
		return false;
	}
	
	public static boolean canCreateWillageAt(Location loc){
		int mindistance = main.config.getInt("mindistance");
		if(getWillageAt(loc) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance, loc.getBlockY(), loc.getBlockZ())) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance, loc.getBlockY(), loc.getBlockZ())) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance, loc.getBlockY(), loc.getBlockZ() + mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance, loc.getBlockY(), loc.getBlockZ() - mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance/2, loc.getBlockY(), loc.getBlockZ() + mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance, loc.getBlockY(), loc.getBlockZ() + mindistance/2)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance/2, loc.getBlockY(), loc.getBlockZ() - mindistance)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance, loc.getBlockY(), loc.getBlockZ() - mindistance/2)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance/2, loc.getBlockY(), loc.getBlockZ() + mindistance/2)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance/2, loc.getBlockY(), loc.getBlockZ() - mindistance/2)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() - mindistance/2, loc.getBlockY(), loc.getBlockZ() + mindistance/2)) != null){
			return false;
		}
		else if(getWillageAt(new Location(loc.getWorld(), loc.getBlockX() + mindistance/2, loc.getBlockY(), loc.getBlockZ() - mindistance/2)) != null){
			return false;
		}
		
		return true;
	}
	
	public static willage getWillageAt(Location loc){
		
		String world = loc.getWorld().getName();
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		
		for(willage will : main.willages){
			Location middle = will.getMiddle();
			int radius = main.levels.get(will.getLvL()).getRadius();
			
			String cWorld =  middle.getWorld().getName();
			int minX = middle.getBlockX() - radius;
			int maxX = middle.getBlockX() + radius;
			int minZ = middle.getBlockZ() - radius;
			int maxZ = middle.getBlockZ() + radius;
			
			if(world.equalsIgnoreCase(cWorld) && minX < x && maxX > x && minZ < z && maxZ > z){
				return will;
			}
			
		}
		return null;
	}
	
	public static boolean hasFee(Player p, List<cost> list){
		
		for(cost c : list){
			if(c.getWhatCost() == whatcost.MONEY){
				
				waluta val = c.getWaluta();
				int amount = c.getAmount();
				
				if(me.panda.api.info.hasMoney(p, amount, val) == false){
					return false;
				}
				
			}
			else {
				
				ItemStack is = c.getItemStack();
				
				if(p.getInventory().contains(is) == false){
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	public static String getNeedFeeMessage(Player p, List<cost> list){
		String ret = main.pref + "§8Aby to zrobic potrzebujesz:";
		message msg = me.panda.economy.main.msg;
		for(cost c : list){
			if(c.getWhatCost() == whatcost.MONEY){
				if(c.getWaluta() == me.panda.objects.waluta.waluta1){
				ret = ret + " §4 " + c.getAmount() + " " + msg.getNameWaluta1() + ",";
				}
				else	if(c.getWaluta() == me.panda.objects.waluta.waluta2){
					ret = ret + " §4 " + c.getAmount() + " " + msg.getNameWaluta2() + ",";
					}
				else	if(c.getWaluta() == me.panda.objects.waluta.waluta3){
					ret = ret + " §4 " + c.getAmount() + " " + msg.getNameWaluta3() + ",";
					}
			}
			else{
				ret = ret + " §4 " + c.getItemStack().getAmount() + " " + c.getItemStack().getType().name() + ",";
			}
		}
		
		return ret;
	}

	public static willage getWillateOnTag(String string) {
		for(willage will : main.willages){
			if(will.getTag().equals(string)){
				return will;
			}
		}
		return null;
	}

	public static willage getWillageOnName(String string) {
		for(willage will : main.willages){
			if(will.getTag().equals(string)){
				return will;
			}
		}
		return null;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		Integer d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}
