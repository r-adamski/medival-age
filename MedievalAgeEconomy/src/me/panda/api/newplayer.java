package me.panda.api;

import me.panda.economy.main;
import me.panda.objects.wallet;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class newplayer {

	public static void createWallet(final Player p){
		
		FileConfiguration cfg = main.plugin.getConfig();
		
		final int waluta1 = cfg.getInt("waluta1start");
		final int waluta2 = cfg.getInt("waluta2start");
		final int waluta3 = cfg.getInt("waluta3start");
		
		Thread t = new Thread(new Runnable(){																						
			@Override
		    public void run() {
		String insert = "INSERT INTO Economy (`nick`, `waluta1`, `waluta2`, `waluta3`) VALUES ('" + p.getName() + "', '" + waluta1 +"', '" + waluta2 + "','" + waluta3 +"')";
		main.mysql.query(insert);
	    }});
	t.start();
	
	main.players_wallets.put(p.getName(), new wallet(waluta1, waluta2, waluta3));
	
	}
	
}
