package me.panda.objects;

public class wallet{

    public Integer waluta1;
    public Integer waluta2;
    public Integer waluta3;

	public wallet(Integer waluta1, Integer waluta2, Integer waluta3){
		this.waluta1 = waluta1;
		this.waluta2 = waluta2;
		this.waluta3 = waluta3;
    }
	public Integer getWaluta1(){ return this.waluta1; }
	public Integer getWaluta2(){ return this.waluta2; }
	public Integer getWaluta3(){ return this.waluta3; }
}
