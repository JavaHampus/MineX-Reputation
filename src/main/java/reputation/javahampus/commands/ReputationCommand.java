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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package reputation.javahampus.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import reputation.javahampus.Reputation;
import reputation.javahampus.data.DataManager;
import reputation.javahampus.inventory.Inventories;
import reputation.javahampus.utils.MessageManager;

import java.util.List;

public class ReputationCommand implements CommandExecutor {

    public ReputationCommand() {}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if(args.length <= 1) {
            player.sendMessage(MessageManager.includePrefix("&cUsage: /reputation <Player> <+/->"));
        } else {
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);

                int currentReps = DataManager.getDataConfig().getInt(target.getUniqueId() + ".reps");
                List<String> hasGivenTo = DataManager.getDataConfig().getStringList(player.getUniqueId() + ".hasGivenTo");

                switch (args[1]) {
                    case "+":
                        DataManager.addReputation(target.getUniqueId(), currentReps);

                        hasGivenTo.add(String.valueOf(target.getUniqueId()));
                        DataManager.addPlayerToList(player.getUniqueId(), hasGivenTo);

                        DataManager.saveDataConfig();
                        DataManager.reloadDataConfig();

                        player.sendMessage(MessageManager.includePrefix("&7You just gave &b" + target.getName() + "&7 a &a+ &7rep."));
                        target.sendMessage(MessageManager.includePrefix("&7You just got a &a+ &7rep from &b" + player.getName() + "&7."));

                        break;
                    case "-":
                        DataManager.removeReputation(target.getUniqueId(), currentReps);

                        hasGivenTo.add(String.valueOf(target.getUniqueId()));
                        DataManager.addPlayerToList(target.getUniqueId(), hasGivenTo);

                        DataManager.saveDataConfig();
                        DataManager.reloadDataConfig();

                        player.sendMessage(MessageManager.includePrefix("&7You just gave &b" + target.getName() + " &7a &c- &7rep."));
                        target.sendMessage(MessageManager.includePrefix("&7You just got a &c- &7rep from &b" + player.getName() + "&7."));

                        break;
                    default:
                        player.openInventory(Inventories.reputationGUI(player, target));
                }
            } else {
                player.sendMessage(MessageManager.includePrefix("&cInvalid player"));
            }
        }

        return true;
    }
}
