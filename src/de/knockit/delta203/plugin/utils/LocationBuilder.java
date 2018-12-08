package de.knockit.delta203.plugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.knockit.delta203.plugin.KnockIt;

public class LocationBuilder {

	String name;
	Location loc;
	
	public LocationBuilder(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}
	
	public LocationBuilder(String name) {
		this.name = name;
	}
	
	public void create() {
		KnockIt.plugin.getConfig().set("Locations." + name + ".world", loc.getWorld().getName());
		KnockIt.plugin.getConfig().set("Locations." + name + ".x", loc.getX());
		KnockIt.plugin.getConfig().set("Locations." + name + ".y", loc.getY());
		KnockIt.plugin.getConfig().set("Locations." + name + ".z", loc.getZ());
		KnockIt.plugin.getConfig().set("Locations." + name + ".yaw", loc.getYaw());
		KnockIt.plugin.getConfig().set("Locations." + name + ".pitch", loc.getPitch());
		KnockIt.plugin.saveConfig();
	}
	
	public Location get() {
		Location loc = new Location(Bukkit.getWorld(KnockIt.plugin.getConfig().getString("Locations." + name + ".world")),
				KnockIt.plugin.getConfig().getDouble("Locations." + name + ".x"), KnockIt.plugin.getConfig().getDouble("Locations." + name + ".y"), KnockIt.plugin.getConfig().getDouble("Locations." + name + ".z"),
				(float) KnockIt.plugin.getConfig().getDouble("Locations." + name + ".yaw"), (float) KnockIt.plugin.getConfig().getDouble("Locations." + name + ".pitch"));
		return loc;
	}
}
