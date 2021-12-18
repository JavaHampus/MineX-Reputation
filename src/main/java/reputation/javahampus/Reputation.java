package reputation.javahampus;

import org.bukkit.plugin.java.JavaPlugin;
import reputation.javahampus.commands.ReputationCommand;
import reputation.javahampus.configs.PlayerDataManager;
import reputation.javahampus.events.PlayerConnection;

public class Reputation extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin has enabled!");

        PlayerDataManager.createConfig();
        PlayerDataManager.getDataConfig().options().copyDefaults(true);
        PlayerDataManager.saveDataConfig();

        registerCommands();
        registerEvents();
    }

    public void registerCommands() {
        getCommand("reputation").setExecutor(new ReputationCommand(this));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerConnection(this), this);
    }
}
