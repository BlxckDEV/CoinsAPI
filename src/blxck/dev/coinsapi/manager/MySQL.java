package blxck.dev.coinsapi.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blxck.dev.coinsapi.Main;
import org.bukkit.Bukkit;

public class MySQL
{
    public static String host = Main.getInstance().getConfig().getString("host").replace("&", "§").toString();
    public static String port = "3306";
    public static String database = Main.getInstance().getConfig().getString("Database").replace("&", "§").toString();
    public static String username = Main.getInstance().getConfig().getString("user").replace("&", "§").toString();
    public static String password = Main.getInstance().getConfig().getString("passwort").replace("&", "§").toString();

    public static Connection con;

    public static boolean isConnected()
    {
        return con != null;
    }

    public static void connect()
    {
        if (!isConnected()) {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                Bukkit.getConsoleSender().sendMessage(Main.pr + "§aEs konnte erfolgreich mit der Datenbank verbunden werden");
            }
            catch (SQLException e)
            {
                Bukkit.getConsoleSender().sendMessage(Main.pr + "§cEs konnte nicht mit der Datenbank verbunden werden");
            }
        }
    }

    public static void disconnect()
    {
        try
        {
            con.close();
            Bukkit.getConsoleSender().sendMessage(Main.pr + "§aDie Verbindung zur Datenbank konnte erfolgreich geschlossen werden");
        }
        catch (SQLException e)
        {
            Bukkit.getConsoleSender().sendMessage(Main.pr + "§cDie Verbindung zur Datenbank konnte nicht geschlossen werden");
        }
    }

    public static PreparedStatement getStatement(String sql)
    {
        if (isConnected()) {
            try
            {
                return con.prepareStatement(sql);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ResultSet getResult(String sql)
    {
        if (isConnected()) {
            try
            {
                PreparedStatement ps = getStatement(sql);
                return ps.executeQuery();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
