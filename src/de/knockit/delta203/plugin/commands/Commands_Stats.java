package de.knockit.delta203.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.mysql.Get_MySQl;

public class Commands_Stats implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				p.sendMessage(KnockIt.prefix + "§fStats");
				p.sendMessage(" §2Kills: §7" + Get_MySQl.getKills(p.getUniqueId().toString()));
				p.sendMessage(" §2Tode: §7" + Get_MySQl.getTode(p.getUniqueId().toString()));
				try {
					p.sendMessage(" §2KD: §7" + (double) (Get_MySQl.getKills(p.getUniqueId().toString()) / Get_MySQl.getTode(p.getUniqueId().toString())));
				}catch(Exception ex) {
					p.sendMessage(" §2KD: §7" + 0.0);
				}
				p.sendMessage(" §2Killstreak: §7" + Get_MySQl.getKillstreak(p.getUniqueId().toString()));
				p.sendMessage(" §2Position im Ranking: §7" + Get_MySQl.getRanking(p.getUniqueId().toString()));
			}if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null) {
					p.sendMessage(KnockIt.prefix + "§fStats von §2" + target.getName());
					p.sendMessage(" §2Kills: §7" + Get_MySQl.getKills(target.getUniqueId().toString()));
					p.sendMessage(" §2Tode: §7" + Get_MySQl.getTode(target.getUniqueId().toString()));
					try {
						p.sendMessage(" §2KD: §7" + (double) (Get_MySQl.getKills(target.getUniqueId().toString()) / Get_MySQl.getTode(target.getUniqueId().toString())));
					}catch(Exception ex) {
						p.sendMessage(" §2KD: §7" + 0.0);
					}
					p.sendMessage(" §2Killstreak: §7" + Get_MySQl.getKillstreak(target.getUniqueId().toString()));
					p.sendMessage(" §2Position im Ranking: §7" + Get_MySQl.getRanking(target.getUniqueId().toString()));
				}else {
					p.sendMessage(KnockIt.prefix + "§cDer Spieler ist nicht online.");
				}
			}
		}
		return false;
	}
}
