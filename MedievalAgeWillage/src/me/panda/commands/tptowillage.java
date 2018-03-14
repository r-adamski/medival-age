package me.panda.commands;


import me.panda.methods.info;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tptowillage {

	CommandSender sender;
	String[] args;
	
	public tptowillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		final Player p = (Player) sender;
		final willage will = info.getPlayerWillage(p);
		
		if(!main.tp.contains(p.getName())){
		p.sendMessage(main.pref + " Teleportacja za: " + main.config.getInt("timetp") + " sekund");
		main.tp.add(p.getName());
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
			@Override
			public void run() {
				main.tp.remove(p.getName());
				p.teleport(will.getHome());
				p.sendMessage(main.pref + " Teleportacja zakonczona");
			}
			}, main.config.getInt("timetp") * 20);
		
	}
	else{
	p.sendMessage(main.pref + " Blad: jestes juz w trakcie teleportacji");	
	}
	}
}
