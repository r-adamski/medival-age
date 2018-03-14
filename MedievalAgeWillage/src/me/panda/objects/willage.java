package me.panda.objects;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;


public class willage {

    public String name;
    public String tag;
    public List<String> members;
    public String leader;
    public Map<String, String> rangs;
	public List<String> ally;
	public List<String> war;
	public Location middle;
	public Location home;
	public int lvl;
	public String newspaper;
	public gateobj gate;
	public rekru rek;
	public int tax;
	
	public willage(String name, String tag, List<String> members, String leader, Map<String, String> rangs, List<String> ally, List<String> war, Location middle, Location home, int lvl, String newspaper, gateobj gate, rekru rek, int tax){
		this.name = name;
		this.tag = tag;
		this.members = members;
		this.leader = leader;
		this.rangs = rangs;
		this.ally = ally;
		this.war = war;
		this.middle = middle;
		this.home = home;
		this.lvl = lvl;
		this.newspaper = newspaper;
		this.gate = gate;
		this.rek = rek;
		this.tax = tax;
    }
	public String getName(){ return this.name; }
	public String getTag(){ return this.tag; }
	public List<String> getMembers(){ return this.members; }
	public String getLeader(){ return this.leader; }
	public Map<String, String> getRangs(){ return this.rangs; }
	public List<String> getAlly(){ return this.ally; }
	public List<String> getWar(){ return this.war; }
	public Location getMiddle(){ return this.middle; }
	public Location getHome(){ return this.home; }
	public Integer getLvL(){ return this.lvl; }
	public String getNewsPaper(){ return this.newspaper; }
	public gateobj getGate(){ return this.gate; }
	public rekru getRekru(){ return this.rek; }
	public Integer getTax(){ return this.tax; }
}
