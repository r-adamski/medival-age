package me.panda.objects;

import org.bukkit.inventory.ItemStack;

public class offer{

	public String seller;
	public ItemStack is;
	public Integer id;
	public Integer price;
	public waluta val;
	
	public offer(String seller, ItemStack is, Integer id, Integer price, waluta val){
		this.seller = seller;
		this.is = is;
		this.id = id;
		this.price = price;
		this.val = val;
    }
	public String getSeller(){ return this.seller; }
	public ItemStack getItem(){ return this.is; }
	public Integer getId(){ return this.id; }
	public Integer getPrice(){ return this.price; }
	public waluta getWaluta(){ return this.val; }
}
