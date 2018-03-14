package me.panda.willage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import me.panda.methods.MySQLDatabase;
import me.panda.methods.load;
import me.panda.methods.scoreboard;
import me.panda.commands.command;
import me.panda.events.join;
import me.panda.events.move;
import me.panda.objects.lvl;
import me.panda.objects.willage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	public static String pref = (ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Wioski" + ChatColor.GRAY + "]");
    public static Logger log = Logger.getLogger("Minecraft");
    public static MySQLDatabase mysql;
    public static boolean MySQL = true;
    public static main plugin;
    public static File configFile;
    public static FileConfiguration config;
    
    public static Map<String, willage> players = new HashMap<String, willage>();
    public static List<willage> willages = new ArrayList<willage>();
    public static Map<Integer, lvl> levels = new HashMap<Integer, lvl>();
    public static Map<String, String> recruitment = new HashMap<String, String>();
    public static List<String> rangs = new ArrayList<String>();
    public static List<String> cd = new ArrayList<String>();
    public static List<String> tp = new ArrayList<String>();
    
    public void onEnable() {    	
    	
		plugin = this;
		
		configFile = new File(getDataFolder(), "config.yml");
    	
    	try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
    	config = new YamlConfiguration();
    	loadYamls();
    	
    	//³¹czenie z MySQL
    	if (config.getString("MySQL.Host") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw host w configu");
            }
            if (config.getString("MySQL.User") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw usera w configu");
                        }
            if (config.getString("MySQL.Password") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw haslo w configu");
                    }
            if (config.getString("MySQL.Database") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw nazwe bazy danych w configu");
                      }
            if (config.getString("MySQL.Port") == null) {
                  MySQL = false;
                  addLog(pref + "MySQL-ustaw port w configu");
                             }

            if (MySQL) {
            	main.mysql = new MySQLDatabase(this, config.getString("MySQL.Host"),config.getString("MySQL.User"), config.getString("MySQL.Port"), config.getString("MySQL.Password"), config.getString("MySQL.Database"));
              addLog(pref + "MySQL-Laczenie...");
              try
              {
            	  main.mysql.open();
              } catch (Exception e) {
                addLog(pref + "MySQL-probemy z laczeniem..." + e.getMessage());
              }

              if (main.mysql.checkConnection()) {
                addLog(pref + "MySQL-pomyslnie polaczono z baza danych");

                if (!main.mysql.checkTable("Willage")) {
                  addLog(pref + "MySQL- Tabela 'Willage' nie istnieje. Tworzenie...");
                  String query = "CREATE TABLE Willage (name text, tag text, members text, leader text, rangs text, ally text, war text, middle text, home text, lvl int, newspaper text, gate int, rekru int, tax int)";
                  main.mysql.createTable(query);
                }
              }
              else {
                addLog(pref + "MySQL-polaczenie nieudane");
              }

              //rejestrowanie eventow
              Bukkit.getServer().getPluginManager().registerEvents(new join(), this);
              Bukkit.getServer().getPluginManager().registerEvents(new move(), this);

        //rejestracja komendy
              getCommand("wioska").setExecutor(new command());

        //ladowanie danych
        load.loadLevels();
        load.loadRangs();
        load.loadMySQLData();
        
        scoreboard.refreshTags();
    }
    }
	public void onDisable() {
    }
	
	//metoda do debugowania
	public static void addLog(String string){
		System.out.println(string); 
	}
	//-------------
    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //---------------
    public void saveYamls() {
        try {
            config.save(configFile);
        } catch (IOException e) {
        	addLog(pref + "blad przy zapisie pliku konfiguracyjnego");
            e.printStackTrace();
        }
    }
    //-----------------
    public void loadYamls() {
        try {
            config.load(configFile);
        } catch (Exception e) {
        	addLog(pref + "blad przy ladowaniu pliku konfiguracyjnego");
            e.printStackTrace();
        }
    }
    private void firstRun() throws Exception {
        if(!configFile.exists()){
        	addLog(pref + "brak pliku konfiguracyjnego. Generowanie...");
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }  
    }
}