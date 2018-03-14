package me.panda.commands;

import me.panda.api.info;
import me.panda.economy.main;
import me.panda.objects.waluta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class playerswallet {

	CommandSender sender;
	String[] args;
	
	@SuppressWarnings("deprecation")
	public playerswallet(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		if(info.hasWallet(Bukkit.getPlayer(args[0]))){
		sender.sendMessage(main.pref + " Pieniadze gracza " + args[0] + " :");
		sender.sendMessage(main.msg.getNameWaluta1() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta1));
		sender.sendMessage(main.msg.getNameWaluta2() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta2));
		sender.sendMessage(main.msg.getNameWaluta3() + ChatColor.GRAY + info.getMoney((Player)sender, waluta.waluta3));
		}
		else{
			sender.sendMessage(main.pref + main.msg.getWrongPlayer());
		}
		
	}
	
}
