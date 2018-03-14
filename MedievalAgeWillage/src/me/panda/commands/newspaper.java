package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class newspaper {

	CommandSender sender;
	String[] args;
	
	public newspaper(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		String news = args[1].replaceAll("&", "§");
		
		manager.setNewsPaper(will, news);
		
	}
	
}
