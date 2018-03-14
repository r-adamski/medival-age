package me.panda.economy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import me.panda.api.MySQLDatabase;
import me.panda.api.load;
import me.panda.commands.commands;
import me.panda.events.interract;
import me.panda.events.join;
import me.panda.objects.message;
import me.panda.objects.offer;
import me.panda.objects.wallet;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
    public static main plugin;
	public static String pref = "";
    public static Logger log = Logger.getLogger("Minecraft");
    public static MySQLDatabase mysql;
    public static boolean MySQL = true;
    
    //mapki z danymi
    public static Map<String, wallet> players_wallets = new HashMap<String, wallet>();
    public static List<offer> market = new ArrayList<offer>();
    public static List<String> cd = new ArrayList<String>();
    public static Map<String, List<String>> history = new HashMap<String, List<String>>();
    public static Map<String, Integer> pages = new HashMap<String, Integer>();
    public static message msg;
    public static int timecd;
    
    public void onEnable() {    	
		plugin = this;
		
		//config
	     plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
    	
    	//³¹czenie z MySQL
    	if (getConfig().getString("MySQL.Host") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw host w configu");
            }
            if (getConfig().getString("MySQL.User") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw usera w configu");
                        }
            if (getConfig().getString("MySQL.Password") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw haslo w configu");
                    }
            if (getConfig().getString("MySQL.Database") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw nazwe bazy danych w configu");
                      }
            if (getConfig().getString("MySQL.Port") == null) {
                  MySQL = false;
                  addLog(pref + "MySQL-ustaw port w configu");
                             }

            if (MySQL) {
            	mysql = new MySQLDatabase(this, getConfig().getString("MySQL.Host"), getConfig().getString("MySQL.User"), getConfig().getString("MySQL.Port"), getConfig().getString("MySQL.Password"), getConfig().getString("MySQL.Database"));
              addLog(pref + "MySQL-Laczenie...");
              try
              {
            	  main.mysql.open();
              } catch (Exception e) {
                addLog(pref + "MySQL-probemy z laczeniem..." + e.getMessage());
              }

              if (main.mysql.checkConnection()) {
                addLog(pref + "MySQL-pomyslnie polaczono z baza danych");

                if (!main.mysql.checkTable("Economy")) {
                  addLog(pref + "MySQL- Tabela 'Economy' nie istnieje. Tworzenie...");
                  String query = "CREATE TABLE Economy (nick text, waluta1 Integer, waluta2 Integer, waluta3 Integer)";
                  main.mysql.createTable(query);
                }
                if (!main.mysql.checkTable("Market")) {
                    addLog(pref + "MySQL- Tabela 'Market' nie istnieje. Tworzenie...");
                    String query = "CREATE TABLE Market (id Integer, seller text, item text, itemamount Integer, itemenchant text, price Integer, waluta Integer)";
                    main.mysql.createTable(query);
                  }
                if (!main.mysql.checkTable("History")) {
                    addLog(pref + "MySQL- Tabela 'History' nie istnieje. Tworzenie...");
                    String query = "CREATE TABLE History (nick text, message text)";
                    main.mysql.createTable(query);
                  }
              }
              else {
                addLog(pref + "MySQL-polaczenie nieudane");
              }

              //rejestrowanie eventow
              Bukkit.getServer().getPluginManager().registerEvents(new join(), this);
              Bukkit.getServer().getPluginManager().registerEvents(new interract(), this);


        //rejestracja komendy
        getCommand("sakiewka").setExecutor(new commands());
        getCommand("sklep").setExecutor(new commands());
        getCommand("oferta").setExecutor(new commands());
        getCommand("economy").setExecutor(new commands());

        //ladowanie do hashmapek z danych z mysql
        load.loadMysqlData();
        load.loadConfigMessages();
        
    }
    }
	public void onDisable() {
		
    }
	
	//metoda do debugowania
	public static void addLog(String string){
		System.out.println(string); 
	}
    
}