package me.panda.events;


import me.panda.api.info;
import me.panda.api.manager;
import me.panda.api.show;
import me.panda.economy.main;
import me.panda.objects.offer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class interract implements Listener{

	   @SuppressWarnings("deprecation")
	@EventHandler 
	    public void OnInventoryClick(final InventoryClickEvent e){
		   if(e.getInventory().getName().contains("Targ")){
			   
			   e.setCancelled(true);
			   
			   if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() == true && e.getCurrentItem().getItemMeta().hasLore() == true && e.getCurrentItem().getItemMeta().hasDisplayName() == true){

			   int slot = e.getSlot();
			   ItemStack item = e.getCursor();
			   
			   if(item != null){
				   if(slot != 51 && slot != 44 && slot != 42 && slot != 43){
				   if(slot == 53){
						
					   int page = main.pages.get(e.getWhoClicked().getName());
					   if(main.market.size() > page * 48){
					   show.showMarket((Player)e.getWhoClicked(), page + 1);
					   }
				   }
				   else if(slot == 52){
					   int page = main.pages.get(e.getWhoClicked().getName());
					   if(page > 1){
					   show.showMarket((Player)e.getWhoClicked(), page - 1);
					   }
				   }
				   else{
					   //kupowanie
					   
					   Integer id = Integer.parseInt(e.getCurrentItem().getItemMeta().getDisplayName());
					   
					   offer ofer = null;
					   for(offer o : main.market){
						   if(o.getId() == id){
							   ofer = o;
						   }
					   }
					   
					   if(ofer != null){
						   if(ofer.getItem().getAmount() >= 1){							   
							   
							   if(info.hasMoney((Player)e.getWhoClicked(), ofer.getPrice(), ofer.getWaluta()) == true){
								   
								   manager.removeMoney((Player)e.getWhoClicked(), ofer.getPrice(), ofer.getWaluta());
								   
								   manager.addMoney(ofer.getSeller(), ofer.getPrice(), ofer.getWaluta());
								   
								   e.getWhoClicked().getInventory().addItem(new ItemStack(ofer.getItem().getType(), 1));
								   
								   manager.updateMarket(ofer.getId());
								   
							   }
							   else{
								   e.getWhoClicked().closeInventory();
								  Player p = (Player) e.getWhoClicked();
								  p.sendMessage(main.pref + main.msg.getWrongAmount());
							   }
						   }
					   }
					   else{
						   e.getWhoClicked().closeInventory();
							  Player p = (Player) e.getWhoClicked();
							  p.sendMessage(main.pref + " Ten przedmiot juz nie istnieje");
					   }
				   }
			   }
			   
			   }
			   }
		   }
	   }
}
