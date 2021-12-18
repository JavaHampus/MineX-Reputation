package reputation.javahampus.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import reputation.javahampus.Reputation;
import reputation.javahampus.configs.PlayerDataManager;
import reputation.javahampus.utils.MessageManager;

import java.util.List;

public class ReputationCommand implements CommandExecutor {

    private final Reputation plugin;

    public ReputationCommand(Reputation plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(MessageManager.convertMessage("&cError occured. Usage: /reputation <Player> <+/->"));
        } else {
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);

                int currentReps = PlayerDataManager.getDataConfig().getInt(target.getUniqueId() + ".reps");
                List<String> hasGivenTo = PlayerDataManager.getDataConfig().getStringList(player.getUniqueId() + ".hasGivenTo");

                switch (args[1]) {
                    case "+":
                        PlayerDataManager.getDataConfig().set(target.getUniqueId() + ".reps", currentReps + 1);

                        hasGivenTo.add(String.valueOf(target.getUniqueId()));
                        PlayerDataManager.getDataConfig().set(player.getUniqueId() + ".hasGivenTo", hasGivenTo);

                        PlayerDataManager.saveDataConfig();
                        PlayerDataManager.reloadDataConfig();

                        player.sendMessage(MessageManager.convertMessage("&aYou just gave " + target.getName() + " a + rep."));
                        target.sendMessage(MessageManager.convertMessage("&aYou just got a + rep from " + player.getName() + "."));

                        break;
                    case "-":
                        PlayerDataManager.getDataConfig().set(target.getUniqueId() + ".reps", currentReps - 1);
                        PlayerDataManager.getDataConfig().getStringList(player.getUniqueId() + ".hasGivenTo").add(String.valueOf(target.getUniqueId()));

                        hasGivenTo.add(String.valueOf(target.getUniqueId()));
                        PlayerDataManager.getDataConfig().set(player.getUniqueId() + ".hasGivenTo", hasGivenTo);

                        PlayerDataManager.saveDataConfig();
                        PlayerDataManager.reloadDataConfig();

                        player.sendMessage(MessageManager.convertMessage("&cYou just gave " + target.getName() + " a - rep."));
                        target.sendMessage(MessageManager.convertMessage("&cYou just got a - rep from " + player.getName() + "."));

                        break;
                    default:
                        player.sendMessage(MessageManager.convertMessage("&cInvalid Type - Possible options: [+/-]"));
                }
            } else {
                player.sendMessage(MessageManager.convertMessage("&cInvalid player"));
            }
        }

        return true;
    }
}
