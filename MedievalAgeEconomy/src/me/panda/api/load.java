package me.panda.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.panda.economy.main;
import me.panda.objects.message;
import me.panda.objects.offer;
import me.panda.objects.wallet;
import me.panda.objects.waluta;



public class load {

	public static void loadMysqlData(){
		
		main.players_wallets.clear();
		main.market.clear();
		main.history.clear();
		
		Thread t = new Thread(new Runnable(){																						
			@Override
		    public void run() {
      	String select = "SELECT * FROM Economy";
  		ResultSet rs = main.mysql.query(select);
  		try {
  			main.addLog(main.pref + " ladowanie danych z mysql ...");
			while(rs.next()){
				String nick = rs.getString("nick");
				Integer waluta1 = rs.getInt("waluta1");
				Integer waluta2 = rs.getInt("waluta2");
				Integer waluta3 = rs.getInt("waluta3");
				
				main.players_wallets.put(nick, new wallet(waluta1, waluta2, waluta3));
			}
			main.addLog(main.pref + " Ladowanie zakonczone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    }});
		t.start();
		//market
		Thread t1 = new Thread(new Runnable(){																						
			@Override
		    public void run() {
      	String select = "SELECT * FROM Market";
  		ResultSet rs = main.mysql.query(select);
  		try {
  			main.addLog(main.pref + " ladowanie danych z mysql ...");
			while(rs.next()){
				String seller = rs.getString("seller");
				Integer price = rs.getInt("price");
				Integer id = rs.getInt("id");
				waluta val ;
				Integer wal = rs.getInt("waluta");
				if(wal == 1){
					val = waluta.waluta1;
				}
				else if(wal ==2){
					val = waluta.waluta2;
				}
				else{
					val = waluta.waluta3;
				}
				
				String item = rs.getString("item");
				Integer itemamount = rs.getInt("itemamount");
				String itemenchant = rs.getString("itemenchant");
				
				ItemStack is = new ItemStack(Material.matchMaterial(item), itemamount);
				
				String[] str = itemenchant.split(":");
				List<String> list = new ArrayList<String>();
				for(String i : str){
					list.add(i);
				}
				
				for(int i = 0 ; i < list.size() ; i++){
					if(list.contains(i+1)){
					String name = list.get(i);
					int power = Integer.parseInt(list.get(i + 1));
					
					is.addEnchantment(Enchantment.getByName(name), power);
					}
					i++;
				}
				
				main.market.add(new  offer(seller, is, id, price, val));
			}
			main.addLog(main.pref + " Ladowanie zakonczone");
			
	        for(Player p : Bukkit.getOnlinePlayers()){
	    		if(info.hasWallet(p) == false){
	    			newplayer.createWallet(p);
	    		}
	    	}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    }});
		t1.start();
		
		
		Thread t11 = new Thread(new Runnable(){																						
			@Override
		    public void run() {
      	String select = "SELECT * FROM History";
  		ResultSet rs = main.mysql.query(select);
  		try {
  			main.addLog(main.pref + " ladowanie danych z mysql ...");
			while(rs.next()){
				String nick = rs.getString("nick");
				String message = rs.getString("message");
				List<String> hist = new ArrayList<String>();
				if(main.history.containsKey(nick)){
					hist = main.history.get(nick);
					hist.add(message);
					main.history.put(nick, hist);
				}
				else{
					hist.add(message);
				main.history.put(nick, hist);
				}
			}
			main.addLog(main.pref + " Ladowanie zakonczone");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    }});
		t11.start();
		
		
	}
	
	
	
	public static void loadConfigMessages(){
		
		final FileConfiguration config = main.plugin.getConfig();
		
		Thread t = new Thread(new Runnable(){																						
			@Override
		    public void run() {
		
		main.timecd = config.getInt("timecd") * 20;
		main.pref = config.getString("prefix").replaceAll("kolor", "§");
				
	     String waluta1 = config.getString("waluta1").replaceAll("kolor", "§");
	     String waluta2 = config.getString("waluta2").replaceAll("kolor", "§");
	     String waluta3 = config.getString("waluta3").replaceAll("kolor", "§");
	    
	     String cdcommands = config.getString("cdcommands").replaceAll("kolor", "§");
	     String sakiewka = config.getString("sakiewka").replaceAll("kolor", "§");
	     String sakiewkapay = config.getString("sakiewkapay").replaceAll("kolor", "§");
	     String sakiewkatransfer = config.getString("sakiewkatransfer").replaceAll("kolor", "§");
	     String sakiewkaplayer = config.getString("sakiewkaplayer").replaceAll("kolor", "§");
	     String sakiewkaremove = config.getString("sakiewkaremove").replaceAll("kolor", "§");
	     String sakiewkagive = config.getString("sakiewkagive").replaceAll("kolor", "§");
	    
	     String onlyplayercan = config.getString("onlyplayercan").replaceAll("kolor", "§");
	     String wronguse = config.getString("wronguse").replaceAll("kolor", "§");
	     String wrongnumber = config.getString("wrongnumber").replaceAll("kolor", "§");
	     String wrongwaluta = config.getString("wrongwaluta").replaceAll("kolor", "§");
	     String wrongplayer = config.getString("wrongplayer").replaceAll("kolor", "§");
	     String wrongamount = config.getString("wrongamount").replaceAll("kolor", "§");
	     String nopermission = config.getString("nopermission").replaceAll("kolor", "§");

	     String sendtransfer = config.getString("sendtransfer").replaceAll("kolor", "§");
	     String gettransfer = config.getString("gettransfer").replaceAll("kolor", "§");
	    
	     String ofertalist = config.getString("ofertalist").replaceAll("kolor", "§");
	     String ofertadelete = config.getString("ofertadelete").replaceAll("kolor", "§");
	     String ofertacreate = config.getString("ofertacreate").replaceAll("kolor", "§");
	    
	     String ofertaremoved = config.getString("ofertaremoved").replaceAll("kolor", "§");
	     String ofertaremoveerror = config.getString("ofertaremoveerror").replaceAll("kolor", "§");
	    
	     String ofertacreaterror = config.getString("ofertacreateerror").replaceAll("kolor", "§");
	     String prihibiteditem = config.getString("prihibiteditem").replaceAll("kolor", "§");
	     String ofertalimit = config.getString("ofertalimit").replaceAll("kolor", "§");
	     String ofertacreatesuccess = config.getString("ofertacreatesuccess").replaceAll("kolor", "§");
				
	     main.msg = new message( waluta1,  waluta2,  waluta3
	 			,  cdcommands,  sakiewka,  sakiewkapay,  sakiewkatransfer,
				 sakiewkaplayer,  sakiewkaremove,  sakiewkagive,  onlyplayercan,
				 wronguse,  wrongnumber,  wrongwaluta,  wrongplayer,  wrongamount,
				 sendtransfer,  gettransfer,  nopermission,  nopermission,  ofertalist,  ofertadelete,  ofertacreate,
				 ofertaremoved,  ofertaremoveerror,  ofertacreaterror,  prihibiteditem,  ofertalimit,  ofertacreatesuccess);
	     
    }});
t.start();
	}
	
}
