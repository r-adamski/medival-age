package me.panda.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pryzklad {

	CommandSender sender;
	String[] args;
	
	public pryzklad(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		
	}
	
}
