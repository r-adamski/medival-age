package me.panda.objects;

import org.bukkit.inventory.ItemStack;


public class cost {
	
	public waluta val;
	public whatcost whatc;
	public ItemStack is;
	public int amount;

	public cost(waluta val, whatcost whatc, ItemStack is, int amount){
		this.val = val;
		this.is = is;
		this.amount = amount;
		this.whatc = whatc;
    }

public waluta getWaluta(){ return this.val; }
public ItemStack getItemStack(){ return this.is; }
public Integer getAmount(){ return this.amount; }
public whatcost getWhatCost(){ return this.whatc; }
	
}
