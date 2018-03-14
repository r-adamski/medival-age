package me.panda.events;

import me.panda.methods.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener{

	@EventHandler
	public static void onJoin(PlayerJoinEvent e){
		scoreboard.refreshTags();
	}
	
}
