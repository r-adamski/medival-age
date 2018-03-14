package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.gateobj;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gate {

	CommandSender sender;
	String[] args;
	
	public gate(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		
		if(args[1].equalsIgnoreCase("otwarta")){
			gateobj gate = gateobj.OPEN;
			manager.setGate(will, gate);
			p.sendMessage(main.pref + " Ustawienia wstepu do wioski zmienone!");
		}
		else if(args[1].equalsIgnoreCase("zamknieta")){
			gateobj gate = gateobj.CLOSE;
			manager.setGate(will, gate);
			p.sendMessage(main.pref + " Ustawienia wstepu do wioski zmienone!");
		}
		else if(args[1].equalsIgnoreCase("dyplomaci")){
			gateobj gate = gateobj.DIPLOMACY;
			manager.setGate(will, gate);
			p.sendMessage(main.pref + " Ustawienia wstepu do wioski zmienone!");
		}
		else{
			sender.sendMessage(main.pref + "Poprawna komenda §4/wioska brama otwarta/zamknieta/dyplomaci");
		}
		
	}
	
}
