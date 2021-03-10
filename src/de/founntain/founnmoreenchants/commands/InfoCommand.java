package de.founntain.founnmoreenchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.sendMessage(ChatColor.YELLOW + "FounnMoreEnchants created by Founntain => https://github.com/Founntain/founnmoreenchants");

        return true;
    }
}
