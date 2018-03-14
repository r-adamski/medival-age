package me.panda.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import me.panda.methods.info;
import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rekru {

	CommandSender sender;
	String[] args;
	
	public rekru(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		willage will = info.getPlayerWillage(p);
		List<String> ptojoin = new ArrayList<String>();
		for(Entry<String, String> e : main.recruitment.entrySet()){
			if(e.getValue().equals(will.getTag())){
				ptojoin.add(e.getKey());
			}
		}
					int curr = 1;
			p.sendMessage(main.pref +" Lista osob chcacych dolaczyc do wioski");
			for(String s : ptojoin){
				p.sendMessage("§4" + curr + ". §c " + s);
				curr++;
			}
			p.sendMessage("§8Wpisz §4/wioska rekrutacja akceptuj/odrzuc <nick> §8 aby dodac osobe do wioski/odrzucic prosbe.");
		
	}
	
}
