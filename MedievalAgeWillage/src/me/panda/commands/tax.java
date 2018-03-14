package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tax {

	CommandSender sender;
	String[] args;
	
	public tax(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		
		if(info.isNumeric(args[1])){
		int tax = Integer.parseInt(args[1]);
		
		int maxtax = main.config.getInt("maxtax");
		
		if(tax <= maxtax){
			manager.setTax(will, tax);
			p.sendMessage(main.pref + " Podatek ustalony!");
		}
		else{
			p.sendMessage(main.pref + " Blad: podatek nie moze byc tak wysoki");
		}
		}
		else{
			p.sendMessage(main.pref + " Blad: zle podana wartosc podatku");
		}
	}
	
}
