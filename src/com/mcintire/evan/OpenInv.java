package com.mcintire.evan;

import org.bukkit.plugin.java.JavaPlugin;

public class OpenInv extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Enabling");
		getCommand("openinv").setExecutor(new CommandOpenInv(this));
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabling");
	}
}
