package de.knockit.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.mysql.Get_MySQl;
import de.knockit.delta203.plugin.utils.GameManager;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class Death implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		e.getDrops().clear();
		Player p = e.getEntity();
		if(e.getEntity().getKiller() != null) {
			Player killer = e.getEntity().getKiller();
			
			p.getInventory().clear();
			p.sendMessage(KnockIt.prefix + "§7Du wurdest von §2" + killer.getName() + " §7getötet.");
			killer.sendMessage(KnockIt.prefix + "§7Du hast §2" + p.getName() + " §7getötet.");
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1, 1);
			
			Get_MySQl.addKills(killer.getUniqueId().toString(), 1);
			if(Get_MySQl.getKillstreak(killer.getUniqueId().toString()) < Get_MySQl.getKills(killer.getUniqueId().toString())) {
				Get_MySQl.setKillstreak(killer.getUniqueId().toString(), Get_MySQl.getKills(killer.getUniqueId().toString()));
			}
			Get_MySQl.addTode(p.getUniqueId().toString(), 1);
			

			GameManager.sendPacketScoreboard(p);
			GameManager.sendPacketScoreboard(killer);
			
		}else {
			p.sendMessage(KnockIt.prefix + "§7Du bist gestorben.");
			Get_MySQl.addTode(p.getUniqueId().toString(), 1);
			
			GameManager.sendPacketScoreboard(p);
			
		}
		
		Bukkit.getScheduler().runTaskLater(KnockIt.plugin, new Runnable() {
            
            @Override
            public void run() {
                    ((CraftPlayer)p).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
            }
		},2);
	}
}
