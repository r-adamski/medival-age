package me.panda.commands;

import java.util.Iterator;

import me.panda.api.info;
import me.panda.economy.main;
import me.panda.objects.offer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class offertdelete {

	CommandSender sender;
	String[] args;
	
	Integer value;
	
	public offertdelete(final CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		boolean consist = false;
		
		if(info.isNumeric(args[1])){
		value = Integer.parseInt(args[1]);
		
		Iterator<offer> it = main.market.iterator();

			while(it.hasNext()){
				offer o = it.next();
				//sprawdzic czy it.next != nul
				if(o.getSeller().equals(sender.getName())){
					if(o.getId() == value){
						ItemStack is = o.getItem();
						
						Player p  = (Player)sender;
						p.getInventory().addItem(is);
						
						consist = true;
						it.remove();
						Thread t = new Thread(new Runnable(){																						
							@Override
						    public void run() {
						String delete = "DELETE FROM Market WHERE id='" + value + "'";
						main.mysql.query(delete);
						    }});
						t.start();
						
					}
				}
				
			}
			
			if(consist == true){
				sender.sendMessage(main.pref + main.msg.getOfertaRemoved());
			}
			else{
				sender.sendMessage(main.pref + main.msg.getOfertaRemoveError());
			}
			
			
		}
		else{
			sender.sendMessage(main.pref + main.msg.getWrongNumber());
		}
	}
	
}
