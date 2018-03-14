package me.panda.commands;


import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setrang {

	CommandSender sender;
	String[] args;
	
	@SuppressWarnings("deprecation")
	public setrang(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		String nicktochange = args[2];
		String rang = args[1];
		
		willage will = info.getPlayerWillage(p);
		if(Bukkit.getPlayer(nicktochange) != null){
		if(info.hasWillage(Bukkit.getPlayer(nicktochange))){
		if(will.getTag().equals(info.getPlayerWillage(Bukkit.getPlayer(nicktochange)).getTag())){
			if(main.rangs.contains(rang)){
				if(!will.getRangs().get(nicktochange).equalsIgnoreCase(rang)){
					manager.setRang(will, nicktochange, rang);
					p.sendMessage(main.pref + " Ranga poprawnie zmieniona");
				}
				else{
					p.sendMessage(main.pref + " Blad: gracz ma juz ta range");
				}
			}
			else{
				p.sendMessage(main.pref + " Blad: istniejace rangi: " + main.rangs.toString());
			}
		}
		else{
			p.sendMessage(main.pref + " Blad: podany gracz nie nalezy do twojej wioski");
		}
		}
		else{
			p.sendMessage(main.pref + " Blad: podany gracz nie nalezy do twojej wioski");
		}
		}
		else{
			p.sendMessage(main.pref + " Blad: podany gracz nie nalezy do twojej wioski");
		}
	}
	
}
