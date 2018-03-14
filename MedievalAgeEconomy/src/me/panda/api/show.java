package me.panda.api;

import java.util.ArrayList;

import me.panda.economy.main;
import me.panda.objects.offer;
import me.panda.objects.waluta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class show {

	public static void showMarket(Player p, int page){
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "[=" + ChatColor.DARK_PURPLE + ChatColor.BOLD + " Targ "  + ChatColor.YELLOW + " =]" + ChatColor.GOLD + " Strona " + page);

		ArrayList<String> lore = new ArrayList<String>();
		
		//odcinacz 
	 	   ItemStack bedrock = new ItemStack(Material.BEDROCK);
	 	   ItemMeta bedrockmetea = bedrock.getItemMeta();
	 	  bedrockmetea.setDisplayName(ChatColor.AQUA + " §l przegroda");
	 	   
			bedrock.setItemMeta(bedrockmetea);
		
			//next 
		 	   @SuppressWarnings("deprecation")
			ItemStack next = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
		 	   ItemMeta nextmeta = next.getItemMeta();
		 	  nextmeta.setDisplayName(ChatColor.GREEN + " §l nastepna strona");
		 	   
		 	    lore.clear();
				lore.add("§2§l Kliknij aby przejsc na nastepna strone.");

				nextmeta.setLore(lore);
				next.setItemMeta(nextmeta);
				
				//back 
			 	   @SuppressWarnings("deprecation")
				ItemStack back = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
			 	   ItemMeta backmeta = back.getItemMeta();
			 	  backmeta.setDisplayName(ChatColor.RED + " §l poprzednia strona");
			 	   
			 	    lore.clear();
					lore.add("§4§l Kliknij aby przejsc na poprzednia strone.");

					backmeta.setLore(lore);
					back.setItemMeta(backmeta);
			
					
					inv.setItem(51, bedrock);
					inv.setItem(44, bedrock);
					inv.setItem(43, bedrock);
					inv.setItem(42, bedrock);
					inv.setItem(53, next);
					inv.setItem(52, back);
					
					if(page == 1){
					for(int i = 0 ; i < 48 ; i++){
						int size = main.market.size() - 1;
						if(size >= i && main.market.size() != 0){
						if(main.market.get(i) != null){
							offer o = main.market.get(i);
							ItemStack is = o.getItem();
							
							 ItemMeta meta = is.getItemMeta();
							 meta.setDisplayName(o.getId() + "");
						 	   
							 String val = "";
							 if(o.getWaluta() == waluta.waluta1){
								 val = main.msg.getNameWaluta1();
							 }
							 else if(o.getWaluta() == waluta.waluta2){
								 val = main.msg.getNameWaluta2();
							 }
							 else if(o.getWaluta() == waluta.waluta3){
								 val = main.msg.getNameWaluta3();
							 }
							 
							 
						 	    lore.clear();
						 	    lore.add("§c §l Kilknij aby kupic.");
								lore.add("§4§l Cena: §6§l" + o.getPrice() + " " + val);
								lore.add("§4§l Sprzedajacy: §6§l" + o.getSeller());

								meta.setLore(lore);
								is.setItemMeta(meta);
								inv.setItem(i, is);
								
						}
						}
					}
					}
					else{
						for(int i = (page - 1) * 48 ; i < page * 48 ; i++){
							int select = 0;
							int size = main.market.size() - 1;
							if(size >= i && main.market.size() != 0){
								if(main.market.get(i) != null){
								offer o = main.market.get(i);
								ItemStack is = o.getItem();
								
								 ItemMeta meta = is.getItemMeta();
								 meta.setDisplayName(o.getId() + "");
							 	   
								 String val = "";
								 if(o.getWaluta() == waluta.waluta1){
									 val = main.msg.getNameWaluta1();
								 }
								 else if(o.getWaluta() == waluta.waluta2){
									 val = main.msg.getNameWaluta2();
								 }
								 else if(o.getWaluta() == waluta.waluta3){
									 val = main.msg.getNameWaluta3();
								 }
								 
								 
							 	    lore.clear();
							 	    lore.add("§c §l Kilknij aby kupic.");
									lore.add("§4§l Cena: §6§l" + o.getPrice() + " " + val);
									lore.add("§4§l Sprzedajacy: §6§l" + o.getSeller());

									meta.setLore(lore);
									is.setItemMeta(meta);
								
									inv.setItem(select, is);
									select++;
							}
							}
						}
					}
					p.openInventory(inv);
			 main.pages.put(p.getName(), page);

	}
}
