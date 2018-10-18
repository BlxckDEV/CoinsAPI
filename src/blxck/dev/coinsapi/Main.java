package blxck.dev.coinsapi;

import blxck.dev.coinsapi.listener.join_listener;
import blxck.dev.coinsapi.manager.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;
    public static String pr = "§8[§aCoins§8] §r";
    public MySQL mysql;
    public void onEnable() {

        loadConfig();
        connectMySQL();

        CoinsAPI.createTable();
        Bukkit.getPluginManager().registerEvents(new join_listener(), this);
    }

    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public void connectMySQL() {
        this.mysql = new MySQL(this.getConfig().getString("host"), this.getConfig().getString("Database"), this.getConfig().getString("user"), this.getConfig().getString("passwort"));

    }


}
