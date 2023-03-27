package me.smodev.superkits.commands;

import me.smodev.superkits.SuperKits;
import me.smodev.superkits.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitsCommand extends AbstractCommand {

    public KitsCommand() {
        super("kits", "kitpvp.kits", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            Inventory kit = Bukkit.createInventory(null, 45, Format.color(SuperKits.getInstance().getConfig().getString("kits.kits-title")));
            for (String i : SuperKits.getInstance().getConfig().getConfigurationSection("kits.content").getKeys(false)) {
                String name = SuperKits.getInstance().getConfig().getString("kits.content." + i + ".name");
                String item = SuperKits.getInstance().getConfig().getString("kits.content." + i + ".item");
                int slot = SuperKits.getInstance().getConfig().getInt("kits.content." + i + ".slot");
                Material math = Material.getMaterial(item);

                ItemStack itemStack = new ItemStack(math);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(name);
                itemStack.setItemMeta(itemMeta);

                kit.setItem(slot, itemStack);
            }

            player.openInventory(kit);
        }
    }
}
