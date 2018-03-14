package me.panda.commands;

import me.panda.api.info;
import me.panda.api.manager;
import me.panda.economy.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pay {

	CommandSender sender;
	String[] args;
	
	Integer value;
	String nick;
	me.panda.objects.waluta waluta;
	
	@SuppressWarnings("deprecation")
	public pay(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		if(info.isNumeric(args[1])){
		value = Integer.parseInt(args[1]);
			if(main.msg.getNameWaluta1().contains(args[2])){
				waluta = me.panda.objects.waluta.waluta1;
			}
			else if(main.msg.getNameWaluta2().contains(args[2])){
				waluta = me.panda.objects.waluta.waluta2;
			}
			else if(main.msg.getNameWaluta3().contains(args[2])){
				waluta = me.panda.objects.waluta.waluta3;
			}
			if(waluta != null){
				nick = args[3];
				if(info.hasWallet(Bukkit.getPlayer(args[3]))){
					
					if(info.getMoney((Player)sender, waluta) >= value){
					
					manager.removeMoney((Player)sender, value, waluta);
					
					if(waluta == me.panda.objects.waluta.waluta1){
					sender.sendMessage(main.pref + main.msg.getSendTransfer() + " " + sender.getName() + " " + value + " " + main.msg.getNameWaluta1());
					manager.addToHistory(nick, "§8Otrzymano przelew od: §6" + sender.getName() + " " + value + " " + main.msg.getNameWaluta1());
					manager.addToHistory(sender.getName(), "§8Wyslano przelew do: §6" + nick + " " + value + " " + main.msg.getNameWaluta1());
					}
					else if(waluta == me.panda.objects.waluta.waluta2){
					sender.sendMessage(main.pref + main.msg.getSendTransfer() + " " + sender.getName()  + " " + value + " " +main.msg.getNameWaluta2());
					manager.addToHistory(nick, "§8Otrzymano przelew od: §6" + sender.getName() + " " + value + " " + main.msg.getNameWaluta2());
					manager.addToHistory(sender.getName(), "§8Wyslano przelew do: §6" + nick + " " + value + " " + main.msg.getNameWaluta2());
					}
					else if(waluta == me.panda.objects.waluta.waluta3){
					sender.sendMessage(main.pref + main.msg.getSendTransfer() + " " + sender.getName()  + " " + value + " " + main.msg.getNameWaluta3());
					manager.addToHistory(nick, "§8Otrzymano przelew od: §6" + sender.getName() + " " + value + " " + main.msg.getNameWaluta3());
					manager.addToHistory(sender.getName(), "§8Wyslano przelew do: §6" + nick + " " + value + " " + main.msg.getNameWaluta3());
					}
					
					manager.addMoney(nick, value, waluta);
					
					if(waluta == me.panda.objects.waluta.waluta1){
					sender.sendMessage(main.pref + main.msg.getGetTransfer() + " " + nick + " " + value + " " + main.msg.getNameWaluta1());
					}
					else if(waluta == me.panda.objects.waluta.waluta2){
					sender.sendMessage(main.pref + main.msg.getGetTransfer() + " " + nick + " " + value + " " +main.msg.getNameWaluta2());
					}
					else if(waluta == me.panda.objects.waluta.waluta3){
					sender.sendMessage(main.pref + main.msg.getGetTransfer() + " " + nick + " " + value + " " + main.msg.getNameWaluta3());
					}
					
					}
					else{
						sender.sendMessage(main.pref + main.msg.getWrongAmount());
					}
					
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
