package me.panda.commands;


import java.util.Map;
import java.util.Map.Entry;

import me.panda.api.info;
import me.panda.economy.main;
import me.panda.objects.offer;
import me.panda.objects.waluta;

import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class offertcreate {

	CommandSender sender;
	String[] args;
	
	Integer price;
	String nick;
	ItemStack is;
	Integer id;
	waluta val;
	
	public offertcreate(final CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		nick = sender.getName();
		
		if(info.isNumeric(args[1])){
		price = Integer.parseInt(args[1]);
		
		if(main.msg.getNameWaluta1().contains(args[2])){
			val = me.panda.objects.waluta.waluta1;
		}
		else if(main.msg.getNameWaluta2().contains(args[2])){
			val = me.panda.objects.waluta.waluta2;
		}
		else if(main.msg.getNameWaluta3().contains(args[2])){
			val = me.panda.objects.waluta.waluta3;
		}
		if(val != null){
		if(info.getPlayerOfferts(sender.getName()) < main.plugin.getConfig().getInt("maxoffers")){
			boolean stop = false;
		/*for( int i = 0 ; stop == true ; i++){
																									<===== XD PÊTLA
		}*/
			int i = 0;
			while(stop == false){
				
				boolean is = true;
				for(offer o : main.market){
					if(o.getId() == i){
						is = false;
					}
				}
				
				if(is == true){
					stop = true;
					id = i;
				}
				i++;
			}
			
			Player p = (Player)sender;
			is = p.getItemInHand();
			if(is != null && !(is.getType().name().equalsIgnoreCase("AIR"))){
			
				if(!(main.plugin.getConfig().getList("prohibiteditems").contains(is.getType().name()))){
					
					p.getInventory().remove(is);
					
					//tworzenie oferty - mapka
					offer o = new offer(nick, is, id, price, val);
					main.market.add(o);
					
					//tworzenie oferty - mysql
					
					int valutacurrent = 0;
					if(val == waluta.waluta1){
						valutacurrent = 1;
					}
					else if(val == waluta.waluta2){
						valutacurrent = 2;
					}
					else if(val == waluta.waluta3){
						valutacurrent = 3;
					}
					
					final int valuta = valutacurrent;
					
					Map<Enchantment, Integer> enchants = is.getEnchantments();
					
					StringBuilder sb = new StringBuilder();
					
					for(Entry<Enchantment, Integer> e : enchants.entrySet()){
						sb.append(e.getKey().getName());
						sb.append(e.getValue());
					}

					final String itemenchant = sb.toString();
					
					
					
					
										
					Thread t = new Thread(new Runnable(){																						
						@Override
					    public void run() {
					String insert = "INSERT INTO Market (`id`, `seller`, `item`, `itemamount`, `itemenchant`, `price`, `waluta`) VALUES ('" + id + "', '" + nick +"', '" + is.getType().name() + "','" + is.getAmount() +"','" + itemenchant +"','" + price +"','" + valuta +"')";
					main.mysql.query(insert);
				    }});
				t.start();
					
				sender.sendMessage(main.pref + main.msg.getOfertaCreateSuccess());
				
				}
				else{
					sender.sendMessage(main.pref + main.msg.getProhibitedItem());
				}
			}
			else{
				sender.sendMessage(main.pref + main.msg.getOfertaCreateError());
			}
		}
		else{
			sender.sendMessage(main.pref + main.msg.getOfertaLimit());
		}
		}
		else{
			sender.sendMessage(main.pref + main.msg.getWrongWaluta());
		}
		
		}
		else{
			sender.sendMessage(main.pref + main.msg.getWrongNumber());
		}
	}
	
}
