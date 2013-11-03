package com.mcintire.evan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
			// We only want one argument;If there were more a fight would happen
			if (args.length != 1) return false;
			// Cant let those pesky robots use this command
			if (!(sender instanceof Player)) return true;
			
			Player player = (Player) sender;
			Player target = plugin.getServer().getPlayer(args[0]);
			
			if (target != null) {
				Inventory targetInventory = target.getInventory();
				Inventory inv = Bukkit.createInventory(target, 45, "OpenInv:" + target.getName());
				
				for (int i = 0; i < 36; i++) {
					if (targetInventory.getItem(i) != null) {
						inv.setItem(i, targetInventory.getItem(i));
					}
				}
				int pos = 36;
				for (ItemStack item : target.getInventory().getArmorContents()) {
					if (item != null) inv.setItem(pos, item);
					pos++;
				}
				
				player.openInventory(inv);
			} else {
				player.sendMessage(ChatColor.DARK_RED + "Player Not Found");
				return true;
			}
		}
		return false;
		
	}
	
	@EventHandler
	public void onOpenInvClose(InventoryCloseEvent event) {
		if (event.getInventory().getName().contains("OpenInv")) {
			String invTitle = event.getInventory().getName();
			String[] results = invTitle.split(":");
			Inventory toBeSynced = event.getInventory();
			
			Player target = plugin.getServer().getPlayer(results[1]);
			
			// Syncs the armor.
			// Magic Numbers galore.
			target.getInventory().setBoots(toBeSynced.getItem(36));
			target.getInventory().setLeggings(toBeSynced.getItem(37));
			target.getInventory().setChestplate(toBeSynced.getItem(38));
			target.getInventory().setHelmet(toBeSynced.getItem(39));
			
			// Loops through and syncs all the slots
			for (int i = 0; i < 36; i++) {
				if (toBeSynced.getItem(i) != null) {
					target.getInventory().setItem(i, toBeSynced.getItem(i));
				}
			}
		}
	}
}