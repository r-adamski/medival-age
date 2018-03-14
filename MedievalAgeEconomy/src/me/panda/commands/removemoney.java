package me.panda.commands;

import me.panda.api.info;
import me.panda.api.manager;
import me.panda.economy.main;
import me.panda.objects.waluta;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class removemoney {

	CommandSender sender;
	String[] args;
	
	String nick;
	Integer value;
	waluta wal;
	
	@SuppressWarnings("deprecation")
	public removemoney(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		if(info.isNumeric(args[1])){
			value = Integer.parseInt(args[1]);
				if(main.msg.getNameWaluta1().contains(args[2])){
					wal = waluta.waluta1;
				}
				else if(main.msg.getNameWaluta2().contains(args[2])){
					wal = waluta.waluta2;
				}
				else if(main.msg.getNameWaluta3().contains(args[2])){
					wal = waluta.waluta3;
				}
				if(wal != null){
					nick = args[3];
					if(info.hasWallet(Bukkit.getPlayer(args[3]))){
																		
						if(wal == waluta.waluta1){
						sender.sendMessage(main.pref + " Zabrano graczowi  " + nick + " " + value + " " + main.msg.getNameWaluta1());
						}
						else if(wal == waluta.waluta2){
						sender.sendMessage(main.pref + " Zabrano graczowi  "  + nick + " " + value + " " +main.msg.getNameWaluta2());
						}
						else if(wal == waluta.waluta3){
						sender.sendMessage(main.pref + " Zabrano graczowi  " + nick + " " + value + " " + main.msg.getNameWaluta3());
						}
						
						manager.removeMoney(Bukkit.getPlayer(nick), value, wal);
						
					}
					else{
						sender.sendMessage(main.pref + main.msg.getWrongPlayer());
					}
				}
				else{
					sender.sendMessage(main.pref + main.msg.getWrongWaluta());
				}
			}
			else{
				sender.sendMessage(main.pref + main.msg.getWrongNumber());
			}
		
	}
	
}
