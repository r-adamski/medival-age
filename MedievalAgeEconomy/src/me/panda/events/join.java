package me.panda.events;


import me.panda.api.info;
import me.panda.api.newplayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener{

	@EventHandler
	public static void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(info.hasWallet(p) == false){
			newplayer.createWallet(p);
		}
	}
}
