package me.panda.commands;

import me.panda.api.info;
import me.panda.economy.main;
import me.panda.objects.waluta;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class wallet {

	CommandSender sender;
	String[] args;
	
	public wallet(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		sender.sendMessage(main.pref + " Twoje pieniadze:");
		sender.sendMessage(main.msg.getNameWaluta1() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta1));
		sender.sendMessage(main.msg.getNameWaluta2() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta2));
		sender.sendMessage(main.msg.getNameWaluta3() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta3));
		
	}
	
}
