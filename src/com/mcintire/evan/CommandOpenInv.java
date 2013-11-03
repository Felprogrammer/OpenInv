package com.mcintire.evan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandOpenInv implements CommandExecutor, Listener {
	private OpenInv	plugin;
	
	public CommandOpenInv(OpenInv plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("openinv")) {
			Player p = (Player) sender;
			
			Inventory i = Bukkit.createInventory(p, 45);
			int slot = 0;
			for (ItemStack item : p.getInventory().getContents()) {
				i.setItem(slot, item);
				slot++;
			}
			slot = 36;
			for (ItemStack armorPiece : p.getInventory().getArmorContents()) {
				i.setItem(slot, armorPiece);
				slot++;
			}
			p.openInventory(i);
		}
		return false;
	}
}
