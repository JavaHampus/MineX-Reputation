/**
 * MIT License
 *
 * Copyright (c) 2021-2022 JavaHampus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package reputation.javahampus.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DataManager {

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

    public static void addReputation(UUID uuid, int currentReps) {
        dataConfig.set(uuid + ".reps", currentReps + 1);
    }

    public static void removeReputation(UUID uuid, int currentReps) {
        dataConfig.set(uuid + ".reps", currentReps - 1);
    }

    public static void addPlayerToList(UUID uuid, List<String> list) {
        dataConfig.set(uuid + ".hasGivenTo", list);
    }
}
