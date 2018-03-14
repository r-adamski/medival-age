package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class leave {

	CommandSender sender;
	String[] args;
	
	public leave(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		if(info.hasWillage(p)){
			if(!info.isLeader(p)){
				manager.removeMember(info.getPlayerWillage(p), p.getName());
				p.sendMessage(main.pref + " Poprawnie opuszczono wioske!");
				scoreboard.refreshTags();
			}
			else{
				sender.sendMessage(main.pref + " Blad: nie mozesz opuscic wioski bedac jej wlascicielem.");
			}
		}
		else{
			sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski.");
		}
	}
	
}
