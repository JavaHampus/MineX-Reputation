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

package reputation.javahampus.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import reputation.javahampus.utils.Constants;
import reputation.javahampus.utils.MessageManager;

import java.util.ArrayList;
import java.util.List;

public class Inventories {

    public static Inventory reputationGUI(Player player, Player target) {
        Inventory gui = Bukkit.createInventory(player, 9, MessageManager.convertMessage(Constants.INVENTORY_TITLE));

        ItemStack repPlusItem = new ItemStack(Material.GREEN_DYE);
        ItemMeta repPlusItemMeta = repPlusItem.getItemMeta();
        repPlusItemMeta.setDisplayName(MessageManager.convertMessage("&a+"));

        List<String> repPlusItemLore = new ArrayList<>();
        repPlusItemLore.add(MessageManager.convertMessage("&7Give a positive rep to a player."));
        repPlusItemLore.add(" ");
        repPlusItemLore.add(MessageManager.convertMessage("&7Player: &b" + target.getName()));

        repPlusItemMeta.setLore(repPlusItemLore);
        repPlusItem.setItemMeta(repPlusItemMeta);

        ItemStack repNegativeItem = new ItemStack(Material.RED_DYE);
        ItemMeta repNegativeItemMeta = repNegativeItem.getItemMeta();
        repNegativeItemMeta.setDisplayName(MessageManager.convertMessage("&c-"));

        List<String> repNegativeItemLore = new ArrayList<>();
        repNegativeItemLore.add(MessageManager.convertMessage("&7Give a negative rep to a player."));
        repNegativeItemLore.add(" ");
        repNegativeItemLore.add(MessageManager.convertMessage("&7Player: &b" + target.getName()));

        repNegativeItemMeta.setLore(repNegativeItemLore);
        repNegativeItem.setItemMeta(repNegativeItemMeta);

        gui.setItem(3, repPlusItem);
        gui.setItem(5, repNegativeItem);

        return gui;
    }

    public static Inventory informationGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 27, MessageManager.convertMessage(Constants.INVENTORY_TITLE));

        return gui;
    }
}
