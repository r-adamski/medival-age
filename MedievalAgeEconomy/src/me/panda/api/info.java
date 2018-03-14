package me.panda.api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.panda.economy.main;
import me.panda.objects.offer;
import me.panda.objects.waluta;

public class info {

	public static  Integer getMoney(Player p, waluta waluta){
		Integer money = 0;
		
		if(waluta == me.panda.objects.waluta.waluta1){
			
			money = main.players_wallets.get(p.getName()).getWaluta1();

		}
		else if(waluta == me.panda.objects.waluta.waluta2){
			
			money = main.players_wallets.get(p.getName()).getWaluta2();

		}
		else if(waluta == me.panda.objects.waluta.waluta3){
			
			money = main.players_wallets.get(p.getName()).getWaluta3();
			
		}	
		return money;
	}
	
	public static  Integer getMoneyS(String p, waluta waluta){
		Integer money = 0;
		
		if(waluta == me.panda.objects.waluta.waluta1){
			
			money = main.players_wallets.get(p).getWaluta1();

		}
		else if(waluta == me.panda.objects.waluta.waluta2){
			
			money = main.players_wallets.get(p).getWaluta2();

		}
		else if(waluta == me.panda.objects.waluta.waluta3){
			
			money = main.players_wallets.get(p).getWaluta3();
			
		}	
		return money;
	}
	
	public static boolean hasMoney(Player p, Integer price, waluta waluta){
		
		   boolean hasmoney = true;
			 if(getMoney(p, waluta) < price){
				   hasmoney = false;
			   }
		   return hasmoney;
	}
	
	public static boolean hasWallet(Player p){
		
		if(p != null){
		if(main.players_wallets.containsKey(p.getName())){
			return true;
		}
		}
		
		return false;
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
	
	
	public static void sendCurrentOffers(Player p){
		p.sendMessage(main.pref + " Lista twoich ofert:");
		String ret = "Nie masz zadnych ofert wystawionych w sklepie";
		boolean has = false;
		for(offer o : main.market){
			if(o.getSeller().equals(p.getName())){
				has = true;
				if(o.getItem().getEnchantments().isEmpty()){
					p.sendMessage(ChatColor.DARK_GREEN + "" + o.getId() + ChatColor.DARK_GRAY + " : " + ChatColor.GREEN + o.getItem().getType().name() + " " + ChatColor.GRAY + " ilosc: " + ChatColor.GREEN + o.getItem().getAmount());
				}
				else{
				p.sendMessage(ChatColor.DARK_GREEN + "" + o.getId() + ChatColor.DARK_GRAY + " : " + ChatColor.GREEN + o.getItem().getType().name() + " " + ChatColor.GRAY + " ilosc: " + ChatColor.GREEN + o.getItem().getAmount() + ChatColor.GRAY + " enchanty: " + ChatColor.GREEN + o.getItem().getEnchantments().toString());
			}
			}
		}
		
		if(has == false){
			p.sendMessage(ret);
		}
		
	}

	public static int getPlayerOfferts(String name) {
		int ret = 0;
		
		for(offer o : main.market){
			if(o.getSeller().equals(name)){
				ret++;
			}
		}
		
		return ret;
	}
	
}
