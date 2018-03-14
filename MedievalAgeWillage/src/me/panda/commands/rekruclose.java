package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.panda.objects.rekru;

public class rekruclose {

	CommandSender sender;
	String[] args;
	
	public rekruclose(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		if(info.hasWillage(p)){
			manager.setRekru(info.getPlayerWillage(p), rekru.CLOSE);
			sender.sendMessage(main.pref + " Rekrutacja zamknieta!");
		}
		else{
			sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski.");
		}
	}
	
}
