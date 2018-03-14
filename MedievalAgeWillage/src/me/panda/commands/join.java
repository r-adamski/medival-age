package me.panda.commands;

import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.objects.rekru;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class join {

	CommandSender sender;
	String[] args;
	
	public join(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		if(!info.hasWillage(p)){
			if(info.getWillateOnTag(args[1]) != null){
				willage will = info.getWillateOnTag(args[1]);
				if(will.getRekru() == rekru.CLOSE){
					main.recruitment.put(p.getName(), args[1]);
					p.sendMessage(main.pref + " Prosba o dodanie do wioski wyslana!");
				}
				else{
					manager.addMember(will, p.getName());
					p.sendMessage(main.pref + " Witamy w wiosce!");
					scoreboard.refreshTags();
				}
			}
			else{
				p.sendMessage(main.pref + " Blad: wioska o podanym tagu nie istnieje");
			}
		}
		else{
			p.sendMessage(main.pref + " Blad: nalezysz juz do innej wioski");
		}
	}
	
}
