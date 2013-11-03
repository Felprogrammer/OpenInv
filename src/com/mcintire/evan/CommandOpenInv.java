package com.mcintire.evan;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandOpenInv implements CommandExecutor, Listener {
	private OpenInv	plugin;
	
	public CommandOpenInv(OpenInv plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("openinv")) {
			// We only want one argument;If there were more a fight would happen
			if (args.length != 1) return false;
			// Cant let those pesky robots use this command
			if (!(sender instanceof Player)) return true;
			
			Player p = (Player) sender;
			Player target = plugin.getServer().getPlayer(args[0]);
		}
		return false;
		
	}
}
