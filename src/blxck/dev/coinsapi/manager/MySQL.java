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
    private final String HOST;
    private final String DATABASE;
    private final String USER;
    private final String PASSWORD;
    public static Connection con;

    public MySQL(String host, String database, String user, String password)
    {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public static boolean isConnected()
    {
        return con != null;
    }

      public void connect() {
            try {
                  this.con = java.sql.DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true",
                            this.USER, this.PASSWORD);
                  System.out.println("Die Verbindung zur MySQL war erfolgreich");
                 } catch (SQLException e) {
                  System.out.println("Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
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
