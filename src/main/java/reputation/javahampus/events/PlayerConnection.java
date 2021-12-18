package reputation.javahampus.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import reputation.javahampus.Reputation;
import reputation.javahampus.configs.PlayerDataManager;

import java.util.ArrayList;
import java.util.List;

public class PlayerConnection implements Listener {

    private final Reputation plugin;

    public PlayerConnection(Reputation plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent e) {
        Player player = e.getPlayer();

       if(PlayerDataManager.getDataConfig().get(String.valueOf(player.getUniqueId())) == null) {
           PlayerDataManager.getDataConfig().set(player.getUniqueId() + ".reps", 0);

           List<String> hasGivenTo = new ArrayList<>();
           hasGivenTo.add(String.valueOf(player.getUniqueId()));

           PlayerDataManager.getDataConfig().set(player.getUniqueId() + ".hasGivenTo", hasGivenTo);

           PlayerDataManager.saveDataConfig();
           PlayerDataManager.reloadDataConfig();
       }
    }
}
