package de.knockit.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import de.knockit.delta203.plugin.GameState;
import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.mysql.Get_MySQl;
import de.knockit.delta203.plugin.utils.GameManager;
import de.knockit.delta203.plugin.utils.ItemBuilder;
import de.knockit.delta203.plugin.utils.LocationBuilder;

public class Join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(KnockIt.prefix + "§7Der Spieler §2" + p.getName() + " §7hat das Spiel betreten.");
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		
		Get_MySQl.register(p.getUniqueId().toString());
		
		GameManager.states.put(p, GameState.SAVEZONE);
		GameManager.sendPacketScoreboard(p);
		
		p.getInventory().setItem(8, ItemBuilder.easy(new ItemStack(Material.MAGMA_CREAM), "§cZurück zur Lobby", "§7Gehe zur Lobby zurück"));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIt.plugin, new Runnable() {
			
			@Override
			public void run() {
				p.teleport(new LocationBuilder("lobbyspawn").get());
			}
		}, 2);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			try {
				if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück zur Lobby")) {
					KnockIt.sendOtherServer(p, "Lobby-1");
				}
			}catch(Exception ex) {}
		}
	}
}
