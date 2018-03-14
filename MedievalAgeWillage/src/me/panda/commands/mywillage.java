package me.panda.commands;

import me.panda.methods.info;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mywillage {

	CommandSender sender;
	String[] args;
	
	public mywillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		
		if(info.hasWillage(p)){
			willage will = main.players.get(p.getName());
			
			p.sendMessage(main.pref + "§4 " + will.getName());
			p.sendMessage("§8Wlasciciel: §4" + will.getLeader());
			p.sendMessage("§8Czlonkowie: §4" + will.getRangs().toString());
			p.sendMessage("§8Podatek: §4" + will.getTax());
			p.sendMessage("§8Lokalizacja: §4 x:" + will.getHome().getBlockX() + " z: " + will.getHome().getBlockZ());
			p.sendMessage("§8LvL: §4" + will.getLvL());
			p.sendMessage("§8Gazeta: §4" + will.getNewsPaper());
		}
		else{
			p.sendMessage(main.pref + " Nie nalezysz do zadnej wioski!");
		}
	}
	
}
