package me.panda.commands;


import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class willagelist {

	CommandSender sender;
	String[] args;
	
	public willagelist(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		p.sendMessage(main.pref + " Lista wiosek:");
		for(willage will : main.willages){
			p.sendMessage("§41. §7" + will.getTag() + " " + will.getName() + " §e" + will.getLvL() + " §7 lvl");
		}
		
	}
	
}
