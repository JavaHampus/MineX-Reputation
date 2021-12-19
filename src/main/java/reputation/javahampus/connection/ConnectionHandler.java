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

package reputation.javahampus.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import reputation.javahampus.Reputation;
import reputation.javahampus.data.DataManager;
import reputation.javahampus.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler implements Listener {

    public ConnectionHandler() {}

    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent e) {
        Player player = e.getPlayer();

       if(DataManager.getDataConfig().get(String.valueOf(player.getUniqueId())) == null) {
           DataManager.getDataConfig().set(player.getUniqueId() + ".reps", Constants.DEFAULT_REP_VALUE);

           List<String> hasGivenTo = new ArrayList<>();
           DataManager.getDataConfig().set(player.getUniqueId() + ".hasGivenTo", hasGivenTo);

           DataManager.saveDataConfig();
           DataManager.reloadDataConfig();
       }
    }
}
