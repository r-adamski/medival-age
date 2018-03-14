package me.panda.objects;

import java.util.List;


public class lvl {

	public String name;
    public int radius;
    public List<cost> cos;

	public lvl(String name, int radius,List<cost> cos){
		this.name = name;
		this.radius = radius;
		this.cos = cos;
    }

	public String getName(){ return this.name; }
	public Integer getRadius(){ return this.radius ; }
	public List<cost> getCosts(){ return this.cos; }
}
