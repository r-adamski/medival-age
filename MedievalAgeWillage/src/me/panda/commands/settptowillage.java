package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class settptowillage {

	CommandSender sender;
	String[] args;
	
	public settptowillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		
		Location loc = p.getLocation();
		
		if(info.isInMainWillageArea(p)){
			manager.setHome(will, loc);
			p.sendMessage(main.pref + " Zmieniono miejsce teleportacji do wioski!");
		}
		else{
			p.sendMessage(main.pref + " Blad: nie mozesz tego zrobic poza terenem wioski");
		}
		
	}
	
}
