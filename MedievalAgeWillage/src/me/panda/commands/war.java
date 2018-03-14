package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class war {

	CommandSender sender;
	String[] args;
	
	public war(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		String tag = args[1];
		
		if(!will.getWar().contains(tag)){
			if(!will.getAlly().contains(tag)){
				if(info.getWillateOnTag(tag) != null){
					manager.addWar(will, tag);
					scoreboard.refreshTags();
				}
				else{
					sender.sendMessage(main.pref + " Blad: Wioska o podanym tagu nie istnieje");
				}
			}
			else{
				p.sendMessage(main.pref + " Blad: Wioska o tym tagu jest twoim sojusznikiem");
			}
		}
		else{
			if(info.getWillateOnTag(tag) != null){
				manager.removeWar(will, tag);
				scoreboard.refreshTags();
			}
			else{
				p.sendMessage(main.pref + " Blad: Wiokska o podanym tagu nie istnieje");
			}
		}
		
	}
	
}
