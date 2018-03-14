package me.panda.methods;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import me.panda.objects.cost;
import me.panda.objects.gateobj;
import me.panda.objects.rekru;
import me.panda.objects.waluta;
import me.panda.objects.whatcost;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class manager {

	public static void getFee(Player p, List<cost> list){
		
		for(cost c : list){
			if(c.getWhatCost() == whatcost.MONEY){
				
				waluta val = c.getWaluta();
				int amount = c.getAmount();
				
				me.panda.api.manager.removeMoney(p, amount, val);
				
			}
			else {
				
				ItemStack is = c.getItemStack();
				
				p.getInventory().remove(is);
				
			}
		}
	}
	
	public static void removeWillage(final willage will){
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String delete = "DELETE FROM Willage WHERE tag='" + will.getTag() + "'";
				main.mysql.query(delete);
			}
		});
		t.start();
		
		Iterator<willage> it = main.willages.iterator();
		while(it.hasNext()){
			willage will1 = it.next();
			if(will1.getTag().equals(will.getTag())){
				it.remove();
			}
			else if(will1.getAlly().contains(will.getTag())){
				manager.removeAlly(will1, will.getTag());
			}
			else if(will1.getWar().contains(will.getTag())){
				manager.removeWar(will1, will.getTag());
			}
			
		}
		
		Iterator<Entry<String, willage>> it1 = main.players.entrySet().iterator();
		if(it1.hasNext()){
			Entry<String, willage> e = it1.next();
			
			if(e.getValue().getTag().equals(will.getTag())){
				it.remove();
			}
			else if(e.getValue().getAlly().contains(will.getTag())){
				manager.removeAlly(e.getValue(), will.getTag());
			}
			else if(e.getValue().getWar().contains(will.getTag())){
				manager.removeWar(e.getValue(), will.getTag());
			}
			
		}
		
		
	}
	
	public static void setLeader(final willage will, final String nick){

		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), nick, will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE Willage SET leader='" + nick + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void setRekru(final willage will, final rekru rek){

		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), rek, will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				if(rek == rekru.CLOSE){
				String update = "UPDATE Willage SET rekru='0'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
				}
				else{
					String update = "UPDATE Willage SET rekru='1'WHERE tag='" + will.getTag() + "'";
					main.mysql.query(update);
				}
			}
		});
		t.start();
		
	}
	
	public static void setNewsPaper(final willage will, final String news){

		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), news, will.getGate(), will.getRekru(), will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE Willage SET newspaper='" + news + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void setHome(final willage will, final Location loc){

		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), loc, will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				
				String world = loc.getWorld().getName();
				int x = loc.getBlockX();
				int y = loc.getBlockY();
				int z = loc.getBlockZ();
				
				 StringBuilder sb1 = new StringBuilder();
				 
					 sb1.append(world).append(":");
					 sb1.append(x).append(":");
					 sb1.append(y).append(":");
					 sb1.append(z).append(":");

				 
				 String hom = sb1.toString();
				
				String update = "UPDATE Willage SET home='" + hom + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void setGate(final willage will, final gateobj gate){

		
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), gate, will.getRekru(), will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				
				int gat = 1;
				if(gate == gateobj.DIPLOMACY){
					gat = 2;  
				}
				else if(gate == gateobj.CLOSE){
					gat = 3;
				}
				
				
				String update = "UPDATE Willage SET gate='" + gat + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void addMember(final willage will, final String nick){
		
		PermissionsEx.getUser(nick).addGroup(main.rangs.get(0));
		
			final List<String> members = will.getMembers();
			members.add(nick);
			final Map<String, String> rangs = will.getRangs();
			rangs.put(nick, main.rangs.get(0));
			updateMaps(new willage(will.getName(), will.getTag(), members, will.getLeader(), rangs, will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				
				 StringBuilder sb = new StringBuilder();
				 for(String s : members){
					 sb.append(s).append(":");
				 }
				 
				 String memb = sb.toString();
				
				 StringBuilder sb1 = new StringBuilder();
				 for(Entry<String, String> s : rangs.entrySet()){
					 sb1.append(s.getKey()).append(":");
					 sb1.append(s.getValue()).append(":");
				 }
				 
				 String rang = sb1.toString();
				
				String update = "UPDATE Willage SET members='" + memb + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update);
				String update1 = "UPDATE Willage SET rangs='" + rang + "'WHERE tag='" + will.getTag() + "'";
				main.mysql.query(update1);
			}
		});
		t.start();
		
	}
	
	public static void removeMember(final willage will, final String nick){
		
		PermissionsEx.getUser(nick).removeGroup(main.players.get(nick).getRangs().get(nick));
		
		final List<String> members = will.getMembers();
		members.remove(nick);
		final Map<String, String> rangs = will.getRangs();
		rangs.remove(nick);
		updateMaps(new willage(will.getName(), will.getTag(), members, will.getLeader(), rangs, will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			 StringBuilder sb = new StringBuilder();
			 for(String s : members){
				 sb.append(s).append(":");
			 }
			 
			 String memb = sb.toString();
			
			 StringBuilder sb1 = new StringBuilder();
			 for(Entry<String, String> s : rangs.entrySet()){
				 sb1.append(s.getKey()).append(":");
				 sb1.append(s.getValue()).append(":");
			 }
			 
			 String rang = sb1.toString();
			
			String update = "UPDATE Willage SET members='" + memb + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
			String update1 = "UPDATE Willage SET rangs='" + rang + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update1);
		}
	});
	t.start();
	
}
	
	public static void setWillageLvL(final willage will, final int lvl){
			
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), lvl, will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			String update = "UPDATE Willage SET lvl='" + lvl + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void setRang(final willage will, String nick, String rang){
		
		String oldrang = main.players.get(nick).getRangs().get(nick);
		
		PermissionsEx.getUser(nick).removeGroup(oldrang);
		PermissionsEx.getUser(nick).addGroup(rang);
		
		
		
		final Map<String, String> rangs = will.getRangs();
		rangs.put(nick, rang);
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), rangs, will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			
			 StringBuilder sb1 = new StringBuilder();
			 for(Entry<String, String> s : rangs.entrySet()){
				 sb1.append(s.getKey()).append(":");
				 sb1.append(s.getValue()).append(":");
			 }
			 
			 String rang = sb1.toString();
			
			String update = "UPDATE Willage SET rangs='" + rang + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void addWar(final willage will, final String tag){
		
		final List<String> war = will.getWar();
		war.add(tag);
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), war, will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			 StringBuilder sb1 = new StringBuilder();
			 for(String s : war){
				 sb1.append(s).append(":");
			 }
			 
			 String wa = sb1.toString();
			
			String update = "UPDATE Willage SET war='" + wa + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void removeWar(final willage will, final String tag){
		
		final List<String> war = will.getWar();
		war.remove(tag);
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), war, will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			 StringBuilder sb1 = new StringBuilder();
			 for(String s : war){
				 sb1.append(s).append(":");
			 }
			 
			 String wa = sb1.toString();
			
			String update = "UPDATE Willage SET war='" + wa + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void addAlly(final willage will, final String tag){
		
		final List<String> ally = will.getAlly();
		ally.add(tag);
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), ally, will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			 StringBuilder sb1 = new StringBuilder();
			 for(String s : ally){
				 sb1.append(s).append(":");
			 }
			 
			 String all = sb1.toString();
			
			String update = "UPDATE Willage SET ally='" + all + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void removeAlly(final willage will, final String tag){
		
		final List<String> ally = will.getAlly();
		ally.remove(tag);
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), ally, will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), will.getTax()));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			 StringBuilder sb1 = new StringBuilder();
			 for(String s : ally){
				 sb1.append(s).append(":");
			 }
			 
			 String all = sb1.toString();
			
			String update = "UPDATE Willage SET ally='" + all + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	public static void setTax(final willage will, final int tax){
		
		updateMaps(new willage(will.getName(), will.getTag(), will.getMembers(), will.getLeader(), will.getRangs(), will.getAlly(), will.getWar(), will.getMiddle(), will.getHome(), will.getLvL(), will.getNewsPaper(), will.getGate(), will.getRekru(), tax));
	
	Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			
			String update = "UPDATE Willage SET tax='" + tax + "'WHERE tag='" + will.getTag() + "'";
			main.mysql.query(update);
		}
	});
	t.start();
	
}
	
	private static void updateMaps(willage will){
		
		ListIterator<willage> itw = main.willages.listIterator();
		while(itw.hasNext()){
		willage wil = itw.next();
		if(wil.getName().equals(will.getName())){
			itw.remove();
			itw.add(will);
		}
		}
		
		Iterator<Entry<String, willage>> itp = main.players.entrySet().iterator();
		while(itp.hasNext()){
		Entry<String, willage> wil = itp.next();
		if(wil.getValue().getName().equals(will.getName())){
			itp.remove();
			main.players.put(wil.getKey(), will);
		}
		}
	}
	
}
