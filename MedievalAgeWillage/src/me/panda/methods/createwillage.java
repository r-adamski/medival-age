package me.panda.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.panda.objects.gateobj;
import me.panda.objects.rekru;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class createwillage {
	public static void createWillage(Player p, final String name, final String tag){
		
		final String leader = p.getName();		
		
		final List<String> members = new ArrayList<String>();
		members.add(leader);
		final Map<String, String> rangs = new HashMap<String, String>();
		rangs.put(leader, "zalozyciel");
		final List<String> ally = new ArrayList<String>();
		final  List<String> war = new ArrayList<String>();
		final  Location middle = p.getLocation();
		final  Location home = p.getLocation();
		final int lvl = 0;
		final  String newspaper = "";
		final  gateobj gate = gateobj.OPEN;
		final rekru rek = rekru.CLOSE;
		final int tax = main.config.getInt("taxstart");
		
		 willage will = new willage(name, tag, members, leader, rangs, ally, war, middle, home, lvl, newspaper, gate, rek, tax);
		 main.willages.add(will);
		 main.players.put(leader, will);
		 		 
		 Thread t = new Thread(new Runnable(){
			 @Override
			 public void run(){
				 
					StringBuilder sb = new StringBuilder();
					  sb.append(leader).append(":");

					String members = sb.toString();
					
					StringBuilder sb1 = new StringBuilder();
					
					for(Entry<String, String> e : rangs.entrySet()){
						  sb1.append(e.getKey()).append(":");
						  sb1.append(e.getValue()).append(":");

					}
					
					String rangs = sb1.toString();
					Location loc = middle;
					String world = loc.getWorld().getName();
					int x =  loc.getBlockX();
					int z =  loc.getBlockZ();
					int y =  loc.getBlockY();
					
					StringBuilder sb2 = new StringBuilder();
					  sb2.append(world).append(":");
					  sb2.append(x).append(":");
					  sb2.append(y).append(":");
					  sb2.append(z).append(":");

					String middle = sb2.toString();
					
				 
					String insert = "INSERT INTO Willage (`name`, `tag`, `members`, `leader`, `rangs`, `ally`, `war`, `middle`, `home`, `lvl`, `newspaper`, `gate`, `rekru`) VALUES" +
" ('" + name + "', '" + tag +"', '" + members + "', '" + leader + "', '" + rangs + "', '" + "" + "', '" + "" + "', '" + middle + "', '" + middle + "', '" + "0" + "', '" + "" + "', '" + "1" + "', '" + "1" + "')";
					main.mysql.query(insert);
				 
			 }
		 });
		 t.start();
		 
		 
		 
	}
}
