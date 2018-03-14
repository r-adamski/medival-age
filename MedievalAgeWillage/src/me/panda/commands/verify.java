package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class verify {

	CommandSender sender;
	String[] args;
	
	public verify(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		
		willage will = info.getWillateOnTag(args[1]);
		
		if(will != null){
			if(will.getLvL() == 0){
				manager.setWillageLvL(will, 1);
				p.sendMessage(main.pref + " Wioska zweryfikowana!");
			}
			else{
				p.sendMessage(main.pref + " Blad: wioska jest juz zweryfikowana");
			}
		}
		else{
			p.sendMessage(main.pref + " Blad: wioska o tym tagu nie istnieje");
		}
		
	}
	
}
