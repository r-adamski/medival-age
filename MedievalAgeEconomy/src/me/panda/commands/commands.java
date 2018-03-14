package me.panda.commands;

import java.util.Map.Entry;

import me.panda.api.info;
import me.panda.api.show;
import me.panda.economy.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("economy")){
			if(!(main.cd.contains(sender.getName()))){
				main.cd.add(sender.getName());
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
					@Override
					public void run() {
	        			main.cd.remove(sender.getName());
					}
					}, main.timecd);
				
				sender.sendMessage(main.pref + " Spis komend:");
				sender.sendMessage("§4/sakiewka pomoc §8spis komend od kont graczy");
				sender.sendMessage("§4/oferta §8spis komend do ofert w sklepie");
				sender.sendMessage("§4/sklep §8otwiera sklep");
				
			}
			else{
				sender.sendMessage(main.pref + main.msg.getCdCommands());
			}
		}
		if(cmd.getName().equalsIgnoreCase("sakiewka")){
			if(!(main.cd.contains(sender.getName()))){
				main.cd.add(sender.getName());
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
					@Override
					public void run() {
	        			main.cd.remove(sender.getName());
					}
					}, main.timecd);
				
			//wyœwietla stan konta
			if(args.length == 0){
				if(sender instanceof Player){
				new wallet(sender, args);
				}
			}
			//pomoc
			else if(args.length == 1 && args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("pomoc")){
				new help(sender, args);
			}
			//platnosc
			else if(args[0].equalsIgnoreCase("zaplac")){
				if (sender instanceof Player){
				if(args.length == 4){
					new pay(sender, args);
				}
				else{
					sender.sendMessage(main.pref + main.msg.getWrongUse() +  " /sakiewka zaplac <ile> <waluta> <gracz>");
				}
				}
				else{
					sender.sendMessage(main.pref + main.msg.getOnlyPlayerCan());
				}
			}
			//alias - platnosc
			else if(args[0].equalsIgnoreCase("przelew")){
				if (sender instanceof Player){
				if(args.length == 4){
					new pay(sender, args);
				}
				else{
					sender.sendMessage(main.pref + main.msg.getWrongUse() +  " /sakiewka przelew <ile> <waluta> <gracz>");
				}
				}
				else{
					sender.sendMessage(main.pref + main.msg.getOnlyPlayerCan());
				}
			}
			//sakiewkaplayer
			else if(args.length == 1 && !args[0].equalsIgnoreCase("historia")){
				if(sender.isOp() || sender.hasPermission("economy.admin")){
					new playerswallet(sender, args);
				}
				else{
					sender.sendMessage(main.pref + main.msg.getNoPermission());
				}
			}
			//dodawanie kasy
			else if(args[0].equalsIgnoreCase("dodaj") || args[0].equalsIgnoreCase("add")){
				if(args.length == 4){
				if(sender.isOp() || sender.hasPermission("economy.admin")){
					new givemoney(sender, args);
				}
				else{
					sender.sendMessage(main.pref + main.msg.getNoPermission());
				}
				}
				else{
					sender.sendMessage(main.pref + main.msg.getWrongUse() +  " /sakiewka dodaj <ile> <waluta> <gracz>");
				}
			}
			//odejmowanie kasy
			else if(args[0].equalsIgnoreCase("odejmij") || args[0].equalsIgnoreCase("remove")){
				if(args.length == 4){
				if(sender.isOp() || sender.hasPermission("economy.admin")){
					new removemoney(sender, args);
				}
				else{
					sender.sendMessage(main.pref + main.msg.getNoPermission());
				}
				}
				else{
					sender.sendMessage(main.pref + main.msg.getWrongUse() +  " /sakiewka odejmij <ile> <waluta> <gracz>");
				}
			}
			//historia przelewow
			else if(args[0].equalsIgnoreCase("historia")){
				sender.sendMessage(main.pref + " Historia przelewow:");
				if(main.history.containsKey(sender.getName())){
				for(String s : main.history.get(sender.getName())){
						sender.sendMessage(s);
					
				}
				}
				else{
					sender.sendMessage("Brak wpisow w historii");
				}
			}
			
			
			}
			else{
				sender.sendMessage(main.pref + main.msg.getCdCommands());
			}
		}
		//sklep
		else if(cmd.getName().equalsIgnoreCase("oferta")){
			
			if(!(main.cd.contains(sender.getName()))){
				main.cd.add(sender.getName());
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
					@Override
					public void run() {
	        			main.cd.remove(sender.getName());
					}
					}, main.timecd);
				
				if(args.length == 0){
					//pomoc
						sender.sendMessage(main.pref + " Spis komend:");
						sender.sendMessage(main.msg.getOfertaList());
						sender.sendMessage(main.msg.getOfertaDelete());
						sender.sendMessage(main.msg.getOfertaCreate());
				}
				else if(args.length == 1){
					if(sender instanceof Player){
					if(args[0].equalsIgnoreCase("lista")){
						info.sendCurrentOffers((Player)sender);
					}
					}
					else{
						sender.sendMessage(main.pref + main.msg.getOnlyPlayerCan());
					}
				}
				else if(args[0].equalsIgnoreCase("usun")){
					if(args.length == 2){
						new offertdelete(sender, args);
					}
					else{
						sender.sendMessage(main.pref + main.msg.getWrongUse() + " /oferta usun <id>");
					}
				}
				else if(args[0].equalsIgnoreCase("stworz")){
					if(sender instanceof Player){
					if(args.length == 3){
						new offertcreate(sender, args);
					}
					else{
						sender.sendMessage(main.pref + main.msg.getWrongUse() + " /oferta stworz <cena_za_sztuke> <waluta>");
					}
					}
					else{
						sender.sendMessage(main.pref + main.msg.getOnlyPlayerCan());
					}
				}
				
			}
			else{
				sender.sendMessage(main.pref + main.msg.getCdCommands());
			}
		}
		
		else if(cmd.getName().equalsIgnoreCase("sklep")){
			if(!(main.cd.contains(sender.getName()))){
				main.cd.add(sender.getName());
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
					@Override
					public void run() {
	        			main.cd.remove(sender.getName());
					}
					}, main.timecd);
			
				
		if(args.length == 0){
			if(sender instanceof Player){
			//otwieranie eq z sklepem
				show.showMarket((Player)sender, 1);
			}
			else{
				sender.sendMessage(main.pref +  main.msg.getOnlyPlayerCan());
			}
		}
		
		
			}
			else{
				sender.sendMessage(main.pref + main.msg.getCdCommands());
			}
		
		}
		
		return false;
	}
}
