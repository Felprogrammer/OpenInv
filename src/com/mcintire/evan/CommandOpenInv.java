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
			if (args.length != 1) return true;
			if (!(sender instanceof Player)) return true;
			Player p = (Player) sender;
			Player target = plugin.getServer().getPlayer(args[0]);
			
			if (target != null) {
				Inventory inv = Bukkit.createInventory(p, 45);
				int slot = 0;
				for (int i = 0; i < 36; i++) {
					ItemStack item = target.getInventory().getItem(i);
					if (item != null) {
						inv.setItem(i, item);
					}
				}
				slot = 36;
				for (ItemStack armorPiece : p.getInventory().getArmorContents()) {
					inv.setItem(slot, armorPiece);
					slot++;
				}
				p.openInventory(inv);
				
			}
		} else {
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
			File f = new File(path);
			f.createNewFile();
			HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
			inventory.put(p.getName() + " Inventory", p.getInventory().getContents());
			inventory.put(p.getName() + " Armor", p.getInventory().getArmorContents());
			ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(f));
			oStream.writeObject(inventory);
			oStream.flush();
			oStream.close();
		} catch (Exception e) {
			
		}
		
	}
	
	public void loadInventory() {
		
	}
}
