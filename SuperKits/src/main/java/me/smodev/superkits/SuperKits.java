package me.smodev.superkits;

import lombok.Getter;
import me.smodev.superkits.commands.EditKitsCMD;
import me.smodev.superkits.commands.KitsCommand;
import me.smodev.superkits.listeners.KitsListeners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperKits extends JavaPlugin {

    @Getter
    public static SuperKits instance;

    @Override
    public void onEnable() {
        getLogger().info("Enabling...");
        instance = this;
        saveDefaultConfig();
        new KitsCommand();
        new EditKitsCMD();
        Bukkit.getPluginManager().registerEvents(new KitsListeners(), this);
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling...");
    }
}
