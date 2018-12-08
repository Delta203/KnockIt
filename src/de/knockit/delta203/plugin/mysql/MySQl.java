package de.knockit.delta203.plugin.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.knockit.delta203.plugin.KnockIt;

public class MySQl {

	public static Connection con;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":" + "3306" +"/" + "ytcrew" + "?autoReconnect=true", "root", "deltacool");
			} catch (final SQLException e) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(KnockIt.plugin, new Runnable() {
					
					@Override
					public void run() {
						e.printStackTrace();
					}
				},20);
				System.out.println("MySQl has been connected.");
			}
		}
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("MySQl connection canceled.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		return(con != null);
	}
	
	public static void createTable() {
		try {
			con.prepareStatement("CREATE TABLE IF NOT EXISTS Stats_KnockIt (SpielerUUID VARCHAR(100),Kills INT(16),Tode INT(16),Killstreak INT(16))").executeUpdate();
			System.out.println("MySQl table has been created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
