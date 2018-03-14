package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.panda.objects.rekru;

public class rekruopen {

	CommandSender sender;
	String[] args;
	
	public rekruopen(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		if(info.hasWillage(p)){
			manager.setRekru(info.getPlayerWillage(p), rekru.OPEN);
			sender.sendMessage(main.pref + " Rekrutacja otwarta!");
		}
		else{
			sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski.");
		}
	}
	
}
