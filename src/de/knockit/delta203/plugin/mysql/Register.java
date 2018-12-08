package de.knockit.delta203.plugin.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.knockit.delta203.plugin.KnockIt;

public class Register {

	public Register() {
		register();
	}
	
	/** every 15 minutes */
	private void register() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(KnockIt.plugin, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement ps = MySQl.con.prepareStatement("SELECT * FROM Stats_KnockIt");
					ps.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}, 0, 20 * 60 * 15);
	}
}
