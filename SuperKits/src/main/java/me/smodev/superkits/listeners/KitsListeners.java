package me.smodev.superkits.listeners;

import me.smodev.superkits.SuperKits;
import me.smodev.superkits.utils.Format;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitsListeners implements Listener {

    @EventHandler
    public void inventoryListener(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase(Format.color(SuperKits.getInstance().getConfig().getString("kits.kits-title")))){
            int selectedSlot = e.getRawSlot();

            for(String i : SuperKits.getInstance().getConfig().getConfigurationSection("kits.content").getKeys(false)){
                if(SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".slot") == selectedSlot){
                    for(String str : SuperKits.getInstance().getConfig().getConfigurationSection("kits.content." + i + ".items").getKeys(false)){
                        Material math = Material.getMaterial(str);
                        ItemStack itemStack = new ItemStack(math);
                        ItemMeta itemMeta = itemStack.getItemMeta();

                        for(String ench : SuperKits.getInstance().getConfig().getStringList("kits.content." + i + ".items." + str + ".ench")){
                            String[] splitted = ench.split(";");

                            Enchantment enchantment = Enchantment.getByName(splitted[0]);
                            itemMeta.addEnchant(enchantment, Integer.parseInt(splitted[1]), false);
                        }

                        itemStack.setItemMeta(itemMeta);

                        Material material = itemStack.getType();
                        if(SuperKits.getInstance().getConfig().getBoolean("kits.auto-armorEquip")){
                            if (material == Material.LEATHER_BOOTS || material == Material.IRON_BOOTS || material == Material.CHAINMAIL_BOOTS || material == Material.DIAMOND_BOOTS) {
                                player.getInventory().setBoots(itemStack);
                            } else if (material == Material.LEATHER_LEGGINGS || material == Material.IRON_LEGGINGS || material == Material.CHAINMAIL_LEGGINGS || material == Material.DIAMOND_LEGGINGS) {
                                player.getInventory().setLeggings(itemStack);
                            } else if (material == Material.LEATHER_CHESTPLATE || material == Material.IRON_CHESTPLATE || material == Material.CHAINMAIL_CHESTPLATE || material == Material.DIAMOND_CHESTPLATE) {
                                player.getInventory().setChestplate(itemStack);
                            } else if (material == Material.LEATHER_HELMET || material == Material.IRON_HELMET || material == Material.CHAINMAIL_HELMET || material == Material.DIAMOND_HELMET) {
                                player.getInventory().setHelmet(itemStack);
                            }else{
                                player.getInventory().addItem(itemStack);
                            }
                        }else{
                            player.getInventory().setItem(SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".items." + material.name() + ".slot"), itemStack);
                        }

                    }
                    player.sendMessage(Format.color(SuperKits.getInstance().getConfig().getString("kits.content." + i + ".rescued")));
                    Sound sound = Sound.valueOf(SuperKits.getInstance().getConfig().getString("kits.content." + i + ".sound.name"));
                    int volume = SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".sound.name");
                    int pitch = SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".sound.name");
                    player.playSound(player.getLocation(), sound, volume, pitch);

                }
            }
            player.closeInventory();
        }
    }

    @EventHandler
    public void inventory(InventoryInteractEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Format.color(SuperKits.getInstance().getConfig().getString("kits.kits-title"))) || e.getView().getTitle().contains(Format.color("&bEditando il kit"))){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryMoveItem(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Format.color(SuperKits.getInstance().getConfig().getString("kits.kits-title")))){
            e.setCancelled(true);
        }
    }

}
