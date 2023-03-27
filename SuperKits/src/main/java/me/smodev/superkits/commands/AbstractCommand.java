package me.smodev.superkits.commands;

import me.smodev.superkits.SuperKits;
import me.smodev.superkits.utils.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor {

    String command;
    String permission;
    boolean canConsoleUse;

    public AbstractCommand(String command, String permission, boolean canConsoleUse) {
        this.command = command;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;

        SuperKits.getInstance().getCommand(this.command).setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!label.equals(this.command)) return true;

        if(!canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage(Format.color(SuperKits.getInstance().getConfig().getString("messages.console")));
            return true;
        }

        Player player = (Player) sender;

        if(permission != null && !player.hasPermission(permission)) {
            player.sendMessage(Format.color(SuperKits.getInstance().getConfig().getString("messages.no-perms")));
            return true;
        }

        execute(sender, args);
        return true;
    }

}
