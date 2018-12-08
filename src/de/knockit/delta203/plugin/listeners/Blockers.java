package de.knockit.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import de.knockit.delta203.plugin.GameState;
import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.utils.GameManager;

public class Blockers implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(GameManager.states.get(p) == GameState.SAVEZONE) {
				e.setCancelled(true);
			}else {
				e.setCancelled(false);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBuild(BlockPlaceEvent e) {
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if(e.getBlock().getType() == Material.SANDSTONE) {
				e.setCancelled(false);
				e.setBuild(true);
				Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIt.plugin, new Runnable() {
					
					@Override
					public void run() {
						e.getBlock().setType(Material.REDSTONE_BLOCK);
					}
				}, 20 * 3);
				Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIt.plugin, new Runnable() {
					
					@Override
					public void run() {
						e.getBlock().setType(Material.AIR);
					}
				}, 20 * 6);
			}else {
				e.setCancelled(true);
				e.getPlayer().sendMessage("" + e.getBlock().getType());
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getCause() == DamageCause.FALL) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
	}
}
