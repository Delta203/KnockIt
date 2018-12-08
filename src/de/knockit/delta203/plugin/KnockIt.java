package de.knockit.delta203.plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.knockit.delta203.plugin.commands.Commands_Setup;
import de.knockit.delta203.plugin.commands.Commands_Stats;
import de.knockit.delta203.plugin.listeners.Blockers;
import de.knockit.delta203.plugin.listeners.Death;
import de.knockit.delta203.plugin.listeners.Join;
import de.knockit.delta203.plugin.listeners.Move;
import de.knockit.delta203.plugin.listeners.Quit;
import de.knockit.delta203.plugin.listeners.Respawn;
import de.knockit.delta203.plugin.mysql.MySQl;

public class KnockIt extends JavaPlugin {

	public static String prefix = " §7• §aKnockIt §f| §7";
	public static KnockIt plugin;
	
	@EventHandler
	public void onEnable() {
		plugin = this;

		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		MySQl.connect();
		MySQl.createTable();
		
		CloudServer.getInstance().setMotd("Classic");
		
		getCommand("knockit").setExecutor(new Commands_Setup());
		getCommand("stats").setExecutor(new Commands_Stats());
		
		Bukkit.getPluginManager().registerEvents(new Blockers(), this);
		Bukkit.getPluginManager().registerEvents(new Death(), this);
		Bukkit.getPluginManager().registerEvents(new Join(), this);
		Bukkit.getPluginManager().registerEvents(new Move(), this);
		Bukkit.getPluginManager().registerEvents(new Quit(), this);
		Bukkit.getPluginManager().registerEvents(new Respawn(), this);
		
		Bukkit.broadcastMessage(prefix + "§aerfolgreich geladen");
	}
	

	public static void sendOtherServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		 
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		}catch(IOException ex) {}
		p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
}
