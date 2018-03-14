package me.panda.objects;

public class message{

    public String waluta1;
    public String waluta2;
    public String waluta3;
    
    public String cdcommands;
    public String sakiewka;
    public String sakiewkapay;
    public String sakiewkatransfer;
    public String sakiewkaplayer;
    public String sakiewkaremove;
    public String sakiewkagive;
    
    public String onlyplayercan;
    public String wronguse;
    public String wrongnumber;
    public String wrongwaluta;
    public String wrongplayer;
    public String wrongamount;
    public String nopermission;

    public String sendtransfer;
    public String gettransfer;
    
    public String ofertalist;
    public String ofertadelete;
    public String ofertacreate;
    
    public String ofertaremoved;
    public String ofertaremoveerror;
    
    public String ofertacreaterror;
    public String prihibiteditem;
    public String ofertalimit;
    public String ofertacreatesuccess;
    
	public message(String waluta1, String waluta2, String waluta3
			, String cdcommands, String sakiewka, String sakiewkapay, String sakiewkatransfer,
			String sakiewkaplayer, String sakiewkaremove, String sakiewkagive, String onlyplayercan,
			String wronguse, String wrongnumber, String wrongwaluta, String wrongplayer, String wrongamount,
			String sendtransfer, String gettransfer, String nopremission, String nopermission, String ofertalist, String ofertadelete, String ofertacreate,
			String ofertaremoved, String ofertaremoveerror, String ofertacreateerror, String prihibiteditem, String ofertalimit, String ofertacreatesuccess
			){
		this.waluta1 = waluta1;
		this.waluta2 = waluta2;
		this.waluta3 = waluta3;
		
		this.cdcommands = cdcommands;
		this.sakiewka = sakiewka;
		this.sakiewkapay = sakiewkapay;
		this.sakiewkatransfer = sakiewkatransfer;
		this.sakiewkaplayer = sakiewkaplayer;
		this.sakiewkaremove = sakiewkaremove;
		this.sakiewkagive = sakiewkagive;
		
		this.onlyplayercan = onlyplayercan;
		this.wronguse = wronguse;
		this.wrongnumber = wrongnumber;
		this.wrongwaluta =wrongwaluta;
		this.wrongplayer = wrongplayer;
		this.wrongamount = wrongamount;
		this.nopermission = nopermission;
		
		this.sendtransfer = sendtransfer;
		this.gettransfer = gettransfer;
		
		this.ofertalist = ofertalist;
		this.ofertadelete = ofertadelete;
		this.ofertacreate = ofertacreate;
		this.ofertaremoved = ofertaremoved;
		this.ofertaremoveerror = ofertaremoveerror;
		this.ofertacreaterror = ofertacreateerror;
		this.prihibiteditem = prihibiteditem;
		this.ofertalimit = ofertalimit;
		this.ofertacreatesuccess = ofertacreatesuccess;
    }
	public String getNameWaluta1(){ return this.waluta1; }
	public String getNameWaluta2(){ return this.waluta2; }
	public String getNameWaluta3(){ return this.waluta3; }
	
	public String getCdCommands(){ return this.cdcommands; }
	public String getSakiewka(){ return this.sakiewka; }
	public String getSakiewkaPay(){ return this.sakiewkapay; }
	public String getSakiewkaTransfer(){ return this.sakiewkatransfer; }
	public String getSakiewkaPlayer(){ return this.sakiewkaplayer; }
	public String getSakiewkaRemove(){ return this.sakiewkaremove; }
	public String getSakiewkaGive(){ return this.sakiewkagive; }
	
	public String getOnlyPlayerCan(){ return this.onlyplayercan; }
	public String getWrongUse(){ return this.wronguse; }
	public String getWrongNumber(){ return this.wrongnumber; }
	public String getWrongWaluta(){ return this.wrongwaluta; }
	public String getWrongPlayer(){ return this.wrongplayer; }
	public String getWrongAmount(){ return this.wrongamount; }
	public String getNoPermission(){ return this.nopermission; }
	
	public String getSendTransfer(){ return this.sendtransfer; }
	public String getGetTransfer(){ return this.gettransfer; }
	
	public String getOfertaList(){ return this.ofertalist; }
	public String getOfertaDelete(){ return this.ofertadelete; }
	public String getOfertaCreate(){ return this.ofertacreate; }
	
	public String getOfertaRemoved(){ return this.ofertaremoved; }
	public String getOfertaRemoveError(){ return this.ofertaremoveerror; }
	public String getOfertaCreateError(){ return this.ofertacreaterror; }
	public String getProhibitedItem(){ return this.prihibiteditem; }
	public String getOfertaLimit(){ return this.ofertalimit; }
	public String getOfertaCreateSuccess(){ return this.ofertacreatesuccess; }
}
