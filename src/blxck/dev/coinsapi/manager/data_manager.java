package blxck.dev.coinsapi.manager;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class data_manager
{
    public static File file = new File("plugins/BlackCoins", "MySQL.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
}
