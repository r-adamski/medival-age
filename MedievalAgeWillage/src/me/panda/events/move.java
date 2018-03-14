package me.panda.events;

import me.panda.methods.info;
import me.panda.objects.gateobj;
import me.panda.willage.main;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class move implements Listener{

	@EventHandler
	public static void onMove(PlayerMoveEvent e){
		Location from = e.getFrom();
		Location to = e.getTo();
		if(!info.isInWillageArea(from)){
			if(info.isInWillageArea(to)){
				if(!info.isInMainWillageArea(e.getPlayer())){
				if(info.getWillageAt(to).getGate() == gateobj.CLOSE){
					e.setCancelled(true);
				}
				else if(info.getWillageAt(to).getGate() == gateobj.DIPLOMACY){
					if(!e.getPlayer().hasPermission("willage.diplomacy")){
						e.setCancelled(true);
					}
				}
				}
			}
		}
		
		if(main.tp.contains(e.getPlayer().getName())){
			if(from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ()){
				e.getPlayer().sendMessage(main.pref + " Teleportacja przerwana!");
				main.tp.remove(e.getPlayer().getName());
			}
		}
		
	}
	
}
