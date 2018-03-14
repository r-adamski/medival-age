package me.panda.methods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.panda.objects.cost;
import me.panda.objects.gateobj;
import me.panda.objects.lvl;
import me.panda.objects.message;
import me.panda.objects.rekru;
import me.panda.objects.waluta;
import me.panda.objects.whatcost;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class load {
	
	public static void loadRangs(){
	
		main.rangs.clear();
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				int i = 1;
				while(main.config.getString(i + ".name") != null){
					main.rangs.add(main.config.getString(i + ".name"));
					i++;
				}
			}
		});
		t.start();
		
	}
	
	public static void loadLevels(){
		main.levels.clear();
		
		final FileConfiguration cfg = main.config;

		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				int i = 0;
				while(main.plugin.getConfig().contains("lvl" + i)){

					String name = cfg.getString("lvl" + i + ".name");
					int radius = cfg.getInt("lvl" + i + ".radius");
					List<cost> cos = new ArrayList<cost>();
					waluta val = null;
					
					if(cfg.contains("lvl" + i + ".cost")){
						int c = 1;
						while(cfg.contains("lvl" + i + ".cost." + c)){
							
							String item = cfg.getString("lvl" + i + ".cost." + c + ".item");
							
							message msg = me.panda.economy.main.msg;
							if(msg.getNameWaluta1().contains(item) || msg.getNameWaluta2().contains(item) || msg.getNameWaluta3().contains(item)){
							
								int amount = cfg.getInt("lvl" + i + ".cost." + c + ".amount");
								ItemStack is = null;
								whatcost whatc = whatcost.MONEY;
								
								if(msg.getNameWaluta1().contains(item)){
									val = me.panda.objects.waluta.waluta1;
								}
								else if(msg.getNameWaluta2().contains(item)){
									val = me.panda.objects.waluta.waluta2;
								}
								else if(msg.getNameWaluta3().contains(item)){
									val = me.panda.objects.waluta.waluta3;
								}
								
								cost co = new cost(val, whatc, is, amount);
								cos.add(co);
							}
							else{
							int amount = cfg.getInt("lvl" + i + ".cost." + c + ".amount");
							ItemStack is = new ItemStack(Material.matchMaterial(item), amount);
							
							whatcost whatc = whatcost.ITEM;
							
							cost co = new cost(val, whatc, is, amount);
							cos.add(co);
							
							}
							c++;
						}
					}
					
					main.levels.put(i, new lvl(name, radius, cos));
					
					i++;
				}
			}
		});
		t.start();
	}

	public static void loadMySQLData(){
		
		main.players.clear();
		main.willages.clear();
			main.addLog(main.pref + " ladowanie danych z mysql ...");

		Thread t = new Thread(new Runnable(){																						
			@Override
		    public void run() {
      	String select = "SELECT * FROM Willage";
  		ResultSet rs = main.mysql.query(select);
  		try {
			while(rs.next()){
				main.addLog("mysql");
				String name = rs.getString("name");
				String tag = rs.getString("tag");
				String leader = rs.getString("leader");
				int lvl = rs.getInt("lvl");
				String newspaper = rs.getString("newspaper");
				List<String> members = new ArrayList<String>();
				Map<String, String> rangs = new HashMap<String, String>();
				List<String> ally = new ArrayList<String>();
				List<String> war = new ArrayList<String>();
				Location middle;
				Location home;
				gateobj gate;
				rekru rek;
				int tax = rs.getInt("tax");
				
				int re = rs.getInt("rekru");
				if(re == 0){
					rek = rekru.CLOSE;
				}
				else{
					rek = rekru.OPEN;
				}
				
				String[] mem = rs.getString("members").split(":");
				for(String s : mem){
					members.add(s);
				}
				
				String[] aly = rs.getString("ally").split(":");
				for(String s : aly){
					ally.add(s);
				}
				
				String[] wr = rs.getString("war").split(":");
				for(String s : wr){
					war.add(s);
				}
				
				List<String> list = new ArrayList<String>();
				
				String[] mid = rs.getString("middle").split(":");
				for(String s : mid){
					list.add(s);
				}
				
				World w = Bukkit.getWorld(list.get(0));
				int x = Integer.parseInt(list.get(1));
				int y = Integer.parseInt(list.get(2));
				int z = Integer.parseInt(list.get(3));
				
				middle = new Location(w, x, y, z);
				
				list.clear();
				String[] hom = rs.getString("home").split(":");
				for(String s : hom){
					list.add(s);
				}
				
				World wh = Bukkit.getWorld(list.get(0));
				int xh = Integer.parseInt(list.get(1));
				int yh= Integer.parseInt(list.get(2));
				int zh = Integer.parseInt(list.get(3));
				
				home = new Location(wh, xh, yh, zh);
				
				list.clear();
				String[] rang = rs.getString("rangs").split(":");
				for(String s : rang){
					list.add(s);
				}
				for(int i = 0 ; i < list.size() ; i++){
					int curr = i + 1;
					if(main.rangs.contains(list.get(curr))){
					rangs.put(list.get(i), list.get(curr));
					i++;
					}
					else{
						main.addLog("§4BLAD: GRACZ " + list.get(i) + " MA RANGE KTORA NIE ISTNIEJE W CONFIGU");
						main.addLog(main.rangs.get(0));
						rangs.put(list.get(i), main.rangs.get(0));
						
						i++;
					}
				}
				
				int gat = rs.getInt("gate");
				if(gat == 1){
					gate = gateobj.OPEN;
				}
				else if(gat == 2){
					gate = gateobj.DIPLOMACY;
				}
				else{
					gate = gateobj.CLOSE;
				}
				
				for(String s : members){
					main.players.put(s, new willage(name, tag, members, leader, rangs, ally, war, middle, home, lvl, newspaper, gate, rek, tax));
				}
				
				main.willages.add(new willage(name, tag, members, leader, rangs, ally, war, middle, home, lvl, newspaper, gate, rek, tax));

				
				}
			main.addLog(main.pref + " Ladowanie zakonczone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    }});
		t.start();
	
		
	}
	
}
