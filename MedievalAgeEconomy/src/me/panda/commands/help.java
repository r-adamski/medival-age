package me.panda.commands;

import me.panda.economy.main;

import org.bukkit.command.CommandSender;

public class help {

	CommandSender sender;
	String[] args;
	
	public help(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		if(sender.isOp() || sender.hasPermission("economy.admin")){
			sender.sendMessage(main.pref + " Spis komend:");
			sender.sendMessage(main.msg.getSakiewka());
			sender.sendMessage(main.msg.getSakiewkaPay());
			sender.sendMessage(main.msg.getSakiewkaTransfer());
			sender.sendMessage(main.msg.getSakiewkaPlayer());
			sender.sendMessage(main.msg.getSakiewkaRemove());
			sender.sendMessage(main.msg.getSakiewkaGive());
			sender.sendMessage("§4/sakiewka historia §8 historia przelewow");
		}
		else{
			sender.sendMessage(main.pref + " Spis komend:");
			sender.sendMessage(main.msg.getSakiewka());
			sender.sendMessage(main.msg.getSakiewkaPay());
			sender.sendMessage(main.msg.getSakiewkaTransfer());
			sender.sendMessage("§4/sakiewka historia §8 historia przelewow");
		}
		
	}
	
}
