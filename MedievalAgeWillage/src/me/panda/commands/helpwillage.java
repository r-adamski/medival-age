package me.panda.commands;

import me.panda.methods.info;
import me.panda.willage.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpwillage {

	CommandSender sender;
	String[] args;
	
	public helpwillage(CommandSender sender, String[] args){
		this.sender = sender;
		this.args = args;
		
		Player p = (Player) sender;

		if(info.hasWillage(p)){
			if(p.hasPermission("willage.admin")){
			p.sendMessage(main.pref + " Spis komend:");
			p.sendMessage("§4/wioska <tag> §8 informacje o wiosce.");
			p.sendMessage("§4/wioska opusc §8 opuszczanie wioski.");
			p.sendMessage("§4/wioska rekrutacja zamknieta/otwarta §8 dolaczanie za akceptacja lub automatycznie.");
			p.sendMessage("§4/wioska weryfikuj <tag>§8 weryfikacja wioski.");
			p.sendMessage("§4/wioska rekrutacja §8 pokazuje liste osob chcacych dolaczyc do wioski.");
			p.sendMessage("§4/wioska wojna <tag> §8 wypowiada wojne/odwoluje wojne.");
			p.sendMessage("§4/wioska sojusz <tag> §8 dodaje sojusznika/usuwa sojusznika.");
			p.sendMessage("§4/wioska brama otwarta/zamknieta/dyplomaci §8 ustawienia wstepu do wioski.");
			p.sendMessage("§4/wioska podrozuj §8 teleportacja do wioski.");
			p.sendMessage("§4/wioska ustawdom §8 ustawia miejsce teleportacji do wioski.");
			p.sendMessage("§4/wioska rozbuduj §8 rozbudowywanie wioski.");
			p.sendMessage("§4/wioska usun §8 usuwanie wioski.");
			p.sendMessage("§4/wioska ranga <ranga> <nick> §8 nadaje graczowi range.");
			p.sendMessage("§4/wioska lista §8 lista wiosek.");
			p.sendMessage("§4/wioska podatek <%> §8 ustalasz % podatku od kazdej tranzakcji.");
			p.sendMessage("§4/wioska gazeta <tresc> §8 zmienia wpis w gazecie.");
			}
			else{
				p.sendMessage(main.pref + " Spis komend:");
				p.sendMessage("§4/wioska <tag> §8 informacje o wiosce.");
				p.sendMessage("§4/wioska opusc §8 opuszczanie wioski.");
				p.sendMessage("§4/wioska rekrutacja zamknieta/otwarta §8 dolaczanie za akceptacja lub automatycznie.");
				p.sendMessage("§4/wioska rekrutacja §8 pokazuje liste osob chcacych dolaczyc do wioski.");
				p.sendMessage("§4/wioska wojna <tag> §8 wypowiada wojne/odwoluje wojne.");
				p.sendMessage("§4/wioska sojusz <tag> §8 dodaje sojusznika/usuwa sojusznika.");
				p.sendMessage("§4/wioska brama otwarta/zamknieta/dyplomaci §8 ustawienia wstepu do wioski.");
				p.sendMessage("§4/wioska podrozuj §8 teleportacja do wioski.");
				p.sendMessage("§4/wioska ustawdom §8 ustawia miejsce teleportacji do wioski.");
				p.sendMessage("§4/wioska rozbuduj §8 rozbudowywanie wioski.");
				p.sendMessage("§4/wioska usun §8 usuwanie wioski.");
				p.sendMessage("§4/wioska ranga <ranga> <nick> §8 nadaje graczowi range.");
				p.sendMessage("§4/wioska lista §8 lista wiosek.");
				p.sendMessage("§4/wioska podatek <%> §8 ustalasz % podatku od kazdej tranzakcji.");
				p.sendMessage("§4/wioska gazeta <tresc> §8 zmienia wpis w gazecie.");
			}

		}
		else{
		p.sendMessage(main.pref + " Spis komend:");
		p.sendMessage("§4/wioska stworz <nazwa> <tag> §8 tworzenie wioski.");
		p.sendMessage("§4/wioska <tag> §8 informacje o wiosce.");
		p.sendMessage("§4/wioska dolacz <tag>§8 dolaczanie do wioski.");
		}
	}
	
}
