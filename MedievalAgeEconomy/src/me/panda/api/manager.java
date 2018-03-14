package me.panda.api;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import me.panda.economy.main;
import me.panda.objects.offer;
import me.panda.objects.wallet;
import me.panda.objects.waluta;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class manager {

	public static void addMoney(final String p, Integer money, waluta waluta){
		
		Integer currentmoney = info.getMoneyS(p, waluta);
		final Integer tomoney = currentmoney + money;
		
		if(waluta == me.panda.objects.waluta.waluta1){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta1='" + tomoney + "'WHERE nick='" + p + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p);
			main.players_wallets.put(p, new wallet(tomoney, w.getWaluta2(), w.getWaluta3()));
			
		}
		else if(waluta == me.panda.objects.waluta.waluta2){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta2='" + tomoney + "'WHERE nick='" + p + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p);
			main.players_wallets.put(p, new wallet(w.getWaluta1(), tomoney, w.getWaluta3()));
			
		}
		else if(waluta == me.panda.objects.waluta.waluta3){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta3='" + tomoney + "'WHERE nick='" + p + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p);
			main.players_wallets.put(p, new wallet(w.getWaluta1(), w.getWaluta2(), tomoney));
			
		}
		
	}
	
	
	public static void removeMoney(final Player p, Integer money, waluta waluta){
		
		final Integer tomoney;
		Integer currentmoney = info.getMoney(p, waluta);
		if(!(currentmoney - money < 0)){
		tomoney = currentmoney - money;
		}
		else{
			tomoney = 0;
		}
		
		if(waluta == me.panda.objects.waluta.waluta1){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta1='" + tomoney + "'WHERE nick='" + p.getName() + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p.getName());
			main.players_wallets.put(p.getName(), new wallet(tomoney, w.getWaluta2(), w.getWaluta3()));
			
		}
		else if(waluta == me.panda.objects.waluta.waluta2){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta2='" + tomoney + "'WHERE nick='" + p.getName() + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p.getName());
			main.players_wallets.put(p.getName(), new wallet(w.getWaluta1(), tomoney, w.getWaluta3()));
			
		}
		else if(waluta == me.panda.objects.waluta.waluta3){
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String update = "UPDATE Economy SET waluta3='" + tomoney + "'WHERE nick='" + p.getName() + "'";
			main.mysql.query(update);
			    }});
			t.start();
			
			wallet w = main.players_wallets.get(p.getName());
			main.players_wallets.put(p.getName(), new wallet(w.getWaluta1(), w.getWaluta2(), tomoney));
			
		}
		
	}
	
	public static void updateMarket(final int id){
		Iterator<offer> it = main.market.iterator();
while(it.hasNext()){
	offer o = it.next();
	if(o.getId() == id){
				it.remove();
				final int amount =o.getItem().getAmount() - 1;
				ItemStack is = o.getItem();
				is.setAmount(amount);
				if(amount != 0){
				main.market.add(new offer(o.getSeller(), is, o.getId(), o.getPrice(), o.getWaluta()));
				Thread t = new Thread(new Runnable(){																						
					@Override
				    public void run() {
				String update = "UPDATE Market SET itemamount='" + amount + "'WHERE id='" + id + "'";
				main.mysql.query(update);
				    }});
				t.start();
				}
				else{
					Thread t = new Thread(new Runnable(){																						
						@Override
					    public void run() {
					String update = "DELETE FROM Market WHERE id='" + id + "'";
					main.mysql.query(update);
					    }});
					t.start();
				}
			}
		}
		
	}
	
	public static void addToHistory(final String nick, final String message){
	
		if(!main.history.containsKey(nick)){
			List<String> l = new ArrayList<String>();
			main.history.put(nick, l);
		}
		
		
		int currenthis = main.history.get(nick).size();
		
		if(currenthis < 10){
			
			List<String> hist = new ArrayList<String>();
			
				hist = main.history.get(nick);
				hist.add(message);
				main.history.put(nick, hist);
			
			Thread t = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String insert = "INSERT INTO History (`nick`, `message`) VALUES ('" + nick + "', '" + message +"')";
			main.mysql.query(insert);
		    }});
		t.start();
			
		}
		else{
					
			final String msg = main.history.get(nick).get(0);
						
					Thread t = new Thread(new Runnable(){																						
						@Override
					    public void run() {
					String update = "DELETE FROM History WHERE message='" + msg + "'";
					main.mysql.query(update);
					    }});
					t.start();
					
					List<String> hist = new ArrayList<String>();
					
					hist = main.history.get(nick);
					hist.remove(0);
					main.history.put(nick, hist);					
			
			
			List<String> hist1 = new ArrayList<String>();
			
			hist1 = main.history.get(nick);
			hist1.add(message);
			main.history.put(nick, hist1);
			
			Thread t1 = new Thread(new Runnable(){																						
				@Override
			    public void run() {
			String insert = "INSERT INTO History (`nick`, `message`) VALUES ('" + nick + "', '" + message +"')";
			main.mysql.query(insert);
		    }});
		t1.start();
			
			
		}
		
		
	}
	
}
