package me.panda.methods;

import me.panda.objects.willage;
import me.panda.willage.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class scoreboard {

	public static void refreshTags(){
		
		if(main.config.getBoolean("scoreboard") == true){
			main.addLog("scoreobaord");
			@SuppressWarnings("unused")
			Thread t = new Thread(new Runnable(){
				@Override
				public void run(){

		for(Player p : Bukkit.getOnlinePlayers()){
			//tworzymy teamy wszystkie
			ScoreboardManager sbm = Bukkit.getScoreboardManager();
			Scoreboard sb = sbm.getNewScoreboard();
			
			String pTag = null;
			
			//jezeli gracz ma gildie to ustawiamy tag pGuild
			if(info.hasWillage(p)){
				pTag = main.players.get(p.getName()).getTag();
			}
			
			//tworzenie teamu dla graczy bez gildii
			Team noguild = sb.getTeam("noguild");
			if(noguild == null){
				noguild = sb.registerNewTeam("noguild");
				noguild.setPrefix("§7");
			}
			
			//pêtla po wszystkich gildiach
			for(willage gildia : main.players.values()){
				String gtag = gildia.getTag();
				Team team = sb.getTeam(gtag);
				//jezeli w scoreboardzie niema timu o tagu gildii  tworzymy team
				if(team == null){
					team = sb.registerNewTeam(gtag);
				}
				//zmieniamy kolorki w zaleznosci czy wrog czy sojusznik etc.
				if(pTag == null){
					team.setPrefix("§8[§c" + gtag + "§8] §c");
				}
				else if(pTag == gtag){
						team.setPrefix("§8[§a" + gtag + "§8] §a");
				}
				else if(main.players.get(p.getName()).getAlly().contains(gtag)){
						team.setPrefix("§8[§6" + gtag + "§8] §6");
				}
				else{
						team.setPrefix("§8[§c" + gtag + "§8] §c");
				}
			}
			
			//dodajemy do timowsb refreszowanego gracza odpowiednio reszte graczy na serwie
			for(Player other : Bukkit.getOnlinePlayers()){
				String oTag = null;
				if(info.hasWillage(other)){
					oTag = main.players.get(other.getName()).getTag();
				}
				
				if(oTag == null){
					sb.getTeam("noguild").addPlayer(other);
				}
				else{
					sb.getTeam(oTag).addPlayer(other);
				}
				
			}
			
			//ustawiamy graczowi ktorego refreszujemy nowy scoreboard
			p.setScoreboard(sb);
		}
			}
			});
	}
		
	}
		}
