package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class deletewillage {

	CommandSender sender;
	String[] args;
	
	public deletewillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		
		manager.removeWillage(will);
		scoreboard.refreshTags();
		p.sendMessage(main.pref + " Wioska poprawnie usunieta!");
		
	}
	
}
