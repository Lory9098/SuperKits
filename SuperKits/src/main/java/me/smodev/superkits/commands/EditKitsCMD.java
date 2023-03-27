package me.smodev.superkits.commands;

import me.smodev.superkits.utils.kits.EditKits;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditKitsCMD extends AbstractCommand {
    public EditKitsCMD() {
        super("editkits", "kits.edit", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1){
            Player player = (Player) sender;
            EditKits.editKits(args[0], player);
        }
    }
}
