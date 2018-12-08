package de.knockit.delta203.plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.utils.LocationBuilder;

public class Commands_Setup implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(args.length == 0) {
					sendHelp(p);
				}else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("setspawn")) {
						new LocationBuilder("spawn", p.getLocation()).create();
						p.sendMessage(KnockIt.prefix + "§7Der Spawn wurde erfolgreich gesetzt.");
					}else if(args[0].equalsIgnoreCase("setheight")) {
						new LocationBuilder("level", p.getLocation()).create();
						p.sendMessage(KnockIt.prefix + "§7Die Höhe wurde erfolgreich gesetzt.");
					}else if(args[0].equalsIgnoreCase("skull")) {
						new LocationBuilder("skull", p.getLocation()).create();
						p.sendMessage(KnockIt.prefix + "§7Die Skull Location wurde gesetzt.");
					}else {
						sendHelp(p);
					}
				}else {
					sendHelp(p);
				}
			}
		}
		return false;
	}
	
	private void sendHelp(Player p) {
		p.sendMessage(KnockIt.prefix + "§fHilfe");
		p.sendMessage("§2/knockit setspawn");
		p.sendMessage("§2/knockit setheight");
		p.sendMessage("§2/knockit skull");
	}
}
