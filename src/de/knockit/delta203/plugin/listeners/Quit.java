package de.knockit.delta203.plugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.knockit.delta203.plugin.KnockIt;

public class Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(KnockIt.prefix + "§7Der Spieler §2" + p.getName() + " §7hat das Spiel verlassen.");
	}
}
