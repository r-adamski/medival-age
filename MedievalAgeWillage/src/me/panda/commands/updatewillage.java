package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class updatewillage {

	CommandSender sender;
	String[] args;
	
	public updatewillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		
		int currlvl = will.getLvL();
		int tolvl = currlvl++;
		if(main.levels.get(tolvl) != null){
			if(info.hasFee(p, main.levels.get(tolvl).getCosts())){
				manager.setWillageLvL(will, tolvl);
				manager.getFee(p, main.levels.get(tolvl).getCosts());
				p.sendMessage(main.pref + " Wioska poprawnie awansowala na nastepny poziom!");
			}
			else{
				info.getNeedFeeMessage(p, main.levels.get(tolvl).getCosts());
			}
		}
		else{
			p.sendMessage(main.pref + " Blad: kolejny poziom rozwoju wioski nie istnieje");
		}
		
	}
	
}
