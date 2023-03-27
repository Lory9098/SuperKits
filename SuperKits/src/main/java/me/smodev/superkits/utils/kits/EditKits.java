package me.smodev.superkits.utils.kits;

import me.smodev.superkits.SuperKits;
import me.smodev.superkits.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditKits {

    public static void editKits(String name, Player player){
        if(1 == 1){
            player.sendMessage(Format.color("&4Coming soon.."));
            return;
        }
        Inventory inv = Bukkit.createInventory(null, 45, Format.color("&bEditando il kit " + name));

        for(String i : SuperKits.getInstance().getConfig().getConfigurationSection("kits.content").getKeys(false)){
            if(SuperKits.getInstance().getConfig().getString("kits.content." + i + ".name").equalsIgnoreCase(name)){

                for(String str : SuperKits.getInstance().getConfig().getConfigurationSection("kits.content." + i + ".items").getKeys(false)) {
                    Material math = Material.getMaterial(str);
                    ItemStack itemStack = new ItemStack(math);
                    ItemMeta itemMeta = itemStack.getItemMeta();

                    for (String ench : SuperKits.getInstance().getConfig().getStringList("kits.content." + i + ".items." + str)) {
                        String[] splitted = ench.split(";");

                        Enchantment enchantment = Enchantment.getByName(splitted[0]);
                        itemMeta.addEnchant(enchantment, Integer.parseInt(splitted[1]), false);
                    }

                    itemStack.setItemMeta(itemMeta);
                    inv.setItem(SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".items." + str + ".slot"), itemStack);
                }
                player.openInventory(inv);
                return;
            }
        }

        player.sendMessage(Format.color("&4Nessun kit trovato"));
    }

}
