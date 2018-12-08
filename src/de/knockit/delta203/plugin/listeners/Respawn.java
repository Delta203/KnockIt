package de.knockit.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import de.knockit.delta203.plugin.GameState;
import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.mysql.Get_MySQl;
import de.knockit.delta203.plugin.utils.GameManager;
import de.knockit.delta203.plugin.utils.ItemBuilder;
import de.knockit.delta203.plugin.utils.LocationBuilder;

public class Respawn implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		
		Get_MySQl.register(p.getUniqueId().toString());
		
		GameManager.states.put(p, GameState.SAVEZONE);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIt.plugin, new Runnable() {
			
			@Override
			public void run() {
				p.teleport(new LocationBuilder("spawn").get());
				p.getInventory().setItem(8, ItemBuilder.easy(new ItemStack(Material.MAGMA_CREAM), "§cZurück zur Lobby", "§7Gehe zur Lobby zurück"));
			}
		}, 10);
	}
}
