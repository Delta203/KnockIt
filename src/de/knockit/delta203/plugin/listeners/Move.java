package de.knockit.delta203.plugin.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.knockit.delta203.plugin.GameState;
import de.knockit.delta203.plugin.utils.GameManager;
import de.knockit.delta203.plugin.utils.LocationBuilder;

public class Move implements Listener {

	ArrayList<Player> tote = new ArrayList<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(p.getLocation().getY() <= 0) {
			if(!tote.contains(p)) {
				tote.add(p);
				p.setHealth(0.0);
				p.teleport(new LocationBuilder("spawn").get());
				return;
			}
		}
		
		if(GameManager.states.get(p) == GameState.SAVEZONE) {
			if(p.getLocation().getY() < new LocationBuilder("level").get().getY()) {
				if(tote.contains(p)) {
					tote.remove(p);
				}
				GameManager.states.put(p, GameState.ARENA);
				
				ItemStack stick = new ItemStack(Material.STICK);
				ItemMeta sm = stick.getItemMeta();
				sm.setDisplayName("§5Stick");
				ArrayList<String> lo = new ArrayList<>();
				lo.add("§7Knockback-Stick");
				sm.setLore(lo);
				sm.addEnchant(Enchantment.KNOCKBACK, 4, true);
				stick.setItemMeta(sm);
				p.getInventory().clear();
				p.getInventory().setItem(0, stick);
				p.getInventory().setItem(1, new ItemStack(Material.SANDSTONE, 15));
			}
		}
	}
}
