package me.panda.commands;

import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rekaccept {

	CommandSender sender;
	String[] args;
	
	@SuppressWarnings("deprecation")
	public rekaccept(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		String newp = args[2];
		if(main.recruitment.containsKey(newp)){
			String tagp = main.recruitment.get(newp);
			willage will = info.getPlayerWillage(p);
			if(tagp.equals(will.getTag())){
				main.recruitment.remove(newp);
				manager.addMember(will, newp);
				scoreboard.refreshTags();
				if(Bukkit.getPlayer(newp) != null){
					Bukkit.getPlayer(newp).sendMessage(main.pref + " Twoja prosba o dodanie do wioski zostala rozpatrzona pomyslnie!");
					p.sendMessage(main.pref + " Gracz dolaczyl do wioski!");
				}
			}
			else{
				p.sendMessage(main.pref + " Blad: gracza niema na liscie chcacych dolaczyc do wioski.");
			}
		}
		else{
			p.sendMessage(main.pref + " Blad: gracza niema na liscie chcacych dolaczyc do wioski.");
		}
	}
	
}
