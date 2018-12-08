package de.knockit.delta203.plugin.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Get_MySQl {

	//CREATE TABLE IF NOT EXISTS Stats_KnockIt (SpielerUUID VARCHAR(100),Kills INT(16),Tode INT(16),Killstreak INT(16))
	
	public static int getRanking(String uuid) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Kills FROM Stats_KnockIt WHERE Kills >= ?");
			ps.setInt(1, getKills(uuid));
			ResultSet rs = ps.executeQuery();
			int rank = 0;
			while(rs.next()) {
				rank++;
			}
			return rank;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getKills(String uuid) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Kills FROM Stats_KnockIt WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Kills");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getTode(String uuid) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Tode FROM Stats_KnockIt WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Tode");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getKillstreak(String uuid) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("SELECT Killstreak FROM Stats_KnockIt WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Killstreak");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void addKills(String uuid, int input) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("UPDATE Stats_KnockIt SET Kills = ? WHERE SpielerUUID = ?");
			ps.setInt(1, input + getKills(uuid));
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addTode(String uuid, int input) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("UPDATE Stats_KnockIt SET Tode = ? WHERE SpielerUUID = ?");
			ps.setInt(1, input + getTode(uuid));
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setKillstreak(String uuid, int input) {
		try {
			PreparedStatement ps = MySQl.con.prepareStatement("UPDATE Stats_KnockIt SET Killstreak = ? WHERE SpielerUUID = ?");
			ps.setInt(1, input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void register(String uuid) {
		if(getKills(uuid) == -1) {
			try {
				PreparedStatement ps = MySQl.con.prepareStatement("INSERT INTO Stats_KnockIt (SpielerUUID,Kills,Tode,Killstreak) VALUES (?,?,?,?)");
				ps.setString(1, uuid);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
