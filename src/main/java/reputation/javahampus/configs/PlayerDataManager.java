package reputation.javahampus.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerDataManager {

    private static File dataFile;
    private static FileConfiguration dataConfig;

    public static FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public static void createConfig() {
        dataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Reputation").getDataFolder(), "data.yml");

        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.print("Error creating data file!");
            }
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static void saveDataConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            System.out.print("Error saving data file!");
        }
    }

    public static void reloadDataConfig() {
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}
