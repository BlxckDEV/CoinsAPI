package blxck.dev.coinsapi;

import blxck.dev.coinsapi.listener.join_listener;
import blxck.dev.coinsapi.manager.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String pr = "§8[§aCoins�8] §r";

    public void onEnable() {
        MySQL.connect();
        CoinsAPI.createTable();
        Bukkit.getPluginManager().registerEvents(new join_listener(), this);
    }

    public void onDisable() {}
}
