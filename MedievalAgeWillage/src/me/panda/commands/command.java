package me.panda.commands;


import me.panda.methods.info;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor{

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("wioska")){
			if(!(main.cd.contains(sender.getName()))){
				main.cd.add(sender.getName());
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
					@Override
					public void run() {
	        			main.cd.remove(sender.getName());
					}
					}, main.config.getInt("cdcommands") * 20);
				
				
				if(args.length == 0){
					new mywillage(sender, args);
				}
				else if(args.length == 1){
					if(args[0].equalsIgnoreCase("pomoc") || args[0].equalsIgnoreCase("help")){
						new helpwillage(sender, args);
					}
					else if(args[0].equalsIgnoreCase("opusc")){
						new leave(sender, args);
					}
					else if(args[0].equalsIgnoreCase("podrozuj")){
						if(info.hasWillage((Player)sender)){
							new tptowillage(sender, args);
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else if(args[0].equalsIgnoreCase("ustawdom")){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.sethome") || info.isLeader((Player)sender)){
								new settptowillage(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else if(args[0].equalsIgnoreCase("podrozuj")){
						if(info.hasWillage((Player)sender)){
							new tptowillage(sender, args);
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else if(args[0].equalsIgnoreCase("lista")){
						new willagelist(sender, args);
					}
					else if(args[0].equalsIgnoreCase("rozbuduj")){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.update") || info.isLeader((Player)sender)){
								new updatewillage(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else if(args[0].equalsIgnoreCase("usun")){
						if(info.hasWillage((Player)sender)){
							if(info.isLeader((Player)sender)){
								new deletewillage(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else{
						new willageinfo(sender, args);
					}
				}
				
				if(args.length > 0){
				if(args[0].equalsIgnoreCase("stworz")){
					if(args.length == 3){
						new create(sender, args);
					}
					else{
						sender.sendMessage(main.pref +  " Poprawna komenda: §4/wioska stworz <nazwa> <tag>");
					}
				}
				
				else if(args[0].equalsIgnoreCase("dolacz")){
					if(args.length == 2){
						new join(sender, args);
					}
					else{
						sender.sendMessage(main.pref + " Poprawna komenda: §4 /wioska dolacz <tag>");
					}
				}
				
				else if(args[0].equalsIgnoreCase("rekrutacja")){
						if(info.hasWillage((Player)sender)){
						if(sender.hasPermission("willage.rekru") || info.isLeader((Player)sender)){
							if(args.length == 1){

							new rekru(sender, args);
							
							}
							else if(args.length == 2){
								if(args[1].equalsIgnoreCase("akceptuj")){
									new rekaccept(sender, args);
								}
								else if (args[1].equalsIgnoreCase("odrzuc")){
									new rekdecline(sender, args);
								}
								else if(args[1].equalsIgnoreCase("zamknieta")){
									new rekruclose(sender, args);
								}
								else if(args[1].equalsIgnoreCase("otwarta")){
									new rekruopen(sender, args);
								}
							
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
						}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
				}
				else if(args[0].equalsIgnoreCase("brama")){
					if(args.length == 2){
					if(info.hasWillage((Player)sender)){
						if(sender.hasPermission("willage.gate") || info.isLeader((Player)sender)){
							new gate(sender, args);
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
						}
					}
					else{
						sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
					}
				}
					else{
						sender.sendMessage(main.pref + "Poprawna komenda §4/wioska brama otwarta/zamknieta/dyplomaci");
					}
				}
				else if(args[0].equalsIgnoreCase("wojna")){
					if(args.length == 2){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.war") || info.isLeader((Player)sender)){
								new war(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnje wioski");
						}
					}
					else{
						sender.sendMessage(main.pref + "Poprawna komenda §4/wioska wojna <tag>");
					}
				}
				else if(args[0].equalsIgnoreCase("sojusz")){
					if(args.length == 2){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.ally") || info.isLeader((Player)sender)){
								new ally(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnje wioski");
						}
					}
					else{
						sender.sendMessage(main.pref + "Poprawna komenda §4/wioska sojusz <tag>");
					}
				}
				else if(args[0].equalsIgnoreCase("podatek")){
					if(args.length == 2){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.tax") || info.isLeader((Player)sender)){
								new tax(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnje wioski");
						}
					}
					else{
						sender.sendMessage(main.pref + "Poprawna komenda §4/wioska podatek <%>");
					}
				}
				else if(args[0].equalsIgnoreCase("gazeta")){
					if(args.length == 2){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.news") || info.isLeader((Player)sender)){
								new newspaper(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz uprawnien aby to zrobic");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnje wioski");
						}
					}
					else{
						sender.sendMessage(main.pref + "Poprawna komenda §4/wioska gazeta <tresc>");
					}
				}
				else if(args[0].equalsIgnoreCase("ranga")){
					if(args.length == 3){
						if(info.hasWillage((Player)sender)){
							if(sender.hasPermission("willage.rang") || info.isLeader((Player)sender)){
								new setrang(sender, args);
							}
							else{
								sender.sendMessage(main.pref + " Blad: nie posiadasz do tego uprawnien");
							}
						}
						else{
							sender.sendMessage(main.pref + " Blad: nie nalezysz do zadnej wioski");
						}
					}
					else{
						sender.sendMessage(main.pref + " Poprawna komenda §4/wioska ranga <ranga> <nick>");
					}
				}
				else if(args[0].equalsIgnoreCase("weryfikuj")){
					if(sender.hasPermission("willage.admin")){
						new verify(sender, args);
					}
					else{
						sender.sendMessage(main.pref + " Blad: nie posiadasz do tego uprawnien");
					}
				}
			}
		}
			else{
				sender.sendMessage(main.pref + " Odczekaj chwile zanim wyslesz kolejna komende.");
			}
		}
		return false;
		}
	
}
