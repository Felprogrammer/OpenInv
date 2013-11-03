package com.mcintire.evan;

import org.bukkit.plugin.java.JavaPlugin;

public class OpenInv extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		getLogger().info("Enabling");
	}
	@Override
	public void onDisable()
	{
		getLogger().info("Disabling");
	}
}
