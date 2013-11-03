package com.mcintire.evan;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class CommandOpenInv implements CommandExecutor, Listener {
	private OpenInv	plugin;
	
	public CommandOpenInv(OpenInv plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}
}
