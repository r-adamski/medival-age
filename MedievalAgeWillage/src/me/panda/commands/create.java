package me.panda.commands;

import me.panda.methods.createwillage;
import me.panda.methods.fence;
import me.panda.methods.info;
import me.panda.methods.manager;
import me.panda.methods.scoreboard;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class create {

	CommandSender sender;
	String[] args;
	
	public create(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;
		
		if(!info.hasWillage(p)){
			if(info.canCreateWillageAt(p.getLocation())){
				if(info.hasFee(p, main.levels.get(0).getCosts())){
					if(args[1].length() >= main.config.getInt("minname") && args[1].length() <= main.config.getInt("maxname")){
					if(args[2].length() >= main.config.getInt("mintag") && args[2].length() <= main.config.getInt("maxtag")){
					if(info.getWillageOnName(args[1]) == null){
						if(info.getWillateOnTag(args[2]) == null){
					
							manager.getFee(p, main.levels.get(0).getCosts());
					createwillage.createWillage(p, args[1], args[2]);
					fence.createFence(p.getLocation(), main.levels.get(0).getRadius());
					p.sendMessage(main.pref + " Wioska poprawnie stworzona! Pamietaj aby poprosic administratora o zwerywfikowanie waszej wioski.");
					scoreboard.refreshTags();
						}
						else{
							p.sendMessage(main.pref + " Podany tag jest juz zajety!");
						}
					}
					else{
						p.sendMessage(main.pref + " Podana nazwa wioski jest zajeta!");
					}
					}
					else{
						p.sendMessage(main.pref + " Niepoprawna dlugosc tagu wioski.");
					}
					}
					else{
						p.sendMessage(main.pref + " Niepoprawna dlugosc nazwy wioski.");
					}
				}
				else{
					p.sendMessage(info.getNeedFeeMessage(p, main.levels.get(0).getCosts()));
				}
			}
			else{
				p.sendMessage(main.pref + " Nie mozesz zalozyc wioski w tym miejscu. Sprobuj kawalek dalej!");
			}
		}
		else{
			p.sendMessage(main.pref + " Nie mozesz zalocyc nowej wioski. Nalezysz juz do innej wioski!");
		}
	}
	
}
