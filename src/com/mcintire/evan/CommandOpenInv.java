package com.mcintire.evan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
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
			if (args.length != 1) return true;
			// Cant let those pesky robots use this command
			if (!(sender instanceof Player)) return true;
			
			Player p = (Player) sender;
			Player target = plugin.getServer().getPlayer(args[0]);
			
			// If the target is online
			if (target != null) {
				// Make a new inventory with 45 slots
				Inventory inv = Bukkit.createInventory(p, 45);
				int slot = 0;
				for (int i = 0; i < 36; i++) {
					
					ItemStack item = target.getInventory().getItem(i);
					if (item != null) {
						// If there is an item copy the items into the chest
						// slots
						inv.setItem(i, item);
					}
				}
				// Go to bottom row
				slot = 36;
				// Copy over armor into the bottom row
				for (ItemStack armorPiece : p.getInventory().getArmorContents()) {
					inv.setItem(slot, armorPiece);
					slot++;
				}
				// Open the new inventory
				p.openInventory(inv);
				
			} else {
				
			}
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		saveInventory(p, plugin.getDataFolder() + File.separator + p.getName() + ".bin");
		plugin.getLogger().info("PLAYER LEAVE");
	}
	
	public void saveInventory(Player p, String path) {
		try {
			// Create New File with the player's name
			File f = new File(path);
			f.createNewFile();
			
			// Hashmap Holding String(Players Name + Inventory or Armor) and the
			// ItemStack arrays
			HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
			
			// Put the players stuff in the hashmap
			inventory.put(p.getName() + " Inventory", p.getInventory().getContents());
			inventory.put(p.getName() + " Armor", p.getInventory().getArmorContents());
			
			// Make a new output stream to File f and send the hashmap there
			ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(f));
			oStream.writeObject(inventory);
			
			// Clean Up the Stream
			oStream.flush();
			oStream.close();
		} catch (Exception e) {
			// If bad stuff happens print why it went wrong
			e.printStackTrace();
		}
		
	}
	
	public void loadInventory() {
		
	}
}
