package de.knockit.delta203.plugin.utils;

import java.util.HashMap;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.knockit.delta203.plugin.GameState;
import de.knockit.delta203.plugin.KnockIt;
import de.knockit.delta203.plugin.mysql.Get_MySQl;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class GameManager {

	public static HashMap<Player, GameState> states = new HashMap<>();
	
	public static void sendPackets(Player p, Packet<?> packet) {
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void sendPacketScoreboard(Player p) {
		Scoreboard sb = new Scoreboard();
		ScoreboardObjective obj = sb.registerObjective("§aKnockIt", IScoreboardCriteria.b);
		
		PacketPlayOutScoreboardObjective remove = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardObjective create = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		
		obj.setDisplayName(KnockIt.prefix);
		
		ScoreboardScore s0 = new ScoreboardScore(sb, obj, "");
		ScoreboardScore s1 = new ScoreboardScore(sb, obj, "§2" + Get_MySQl.getRanking(p.getUniqueId().toString()));
		ScoreboardScore s2 = new ScoreboardScore(sb, obj, "§7Ranking:");
		ScoreboardScore s3 = new ScoreboardScore(sb, obj, " ");
		ScoreboardScore s4 = new ScoreboardScore(sb, obj, "§2" + Get_MySQl.getKillstreak(p.getUniqueId().toString()) + " ");
		ScoreboardScore s5 = new ScoreboardScore(sb, obj, "§7Killstreak:");
		ScoreboardScore s6 = new ScoreboardScore(sb, obj, "  ");
		ScoreboardScore s7 = new ScoreboardScore(sb, obj, "§2" + Get_MySQl.getTode(p.getUniqueId().toString()) + "  ");
		ScoreboardScore s8 = new ScoreboardScore(sb, obj, "§7Tode:");
		ScoreboardScore s9 = new ScoreboardScore(sb, obj, "   ");
		ScoreboardScore s10 = new ScoreboardScore(sb, obj, "§2" + Get_MySQl.getKills(p.getUniqueId().toString()) + "   ");
		ScoreboardScore s11 = new ScoreboardScore(sb, obj, "§7Kills:");
		ScoreboardScore s12 = new ScoreboardScore(sb, obj, "    ");
		
		s0.setScore(0);
		s1.setScore(1);
		s2.setScore(2);
		s3.setScore(3);
		s4.setScore(4);
		s5.setScore(5);
		s6.setScore(6);
		s7.setScore(7);
		s8.setScore(8);
		s9.setScore(9);
		s10.setScore(10);
		s11.setScore(11);
		s12.setScore(12);
		
		PacketPlayOutScoreboardScore ps0 = new PacketPlayOutScoreboardScore(s0);
		PacketPlayOutScoreboardScore ps1 = new PacketPlayOutScoreboardScore(s1);
		PacketPlayOutScoreboardScore ps2 = new PacketPlayOutScoreboardScore(s2);
		PacketPlayOutScoreboardScore ps3 = new PacketPlayOutScoreboardScore(s3);
		PacketPlayOutScoreboardScore ps4 = new PacketPlayOutScoreboardScore(s4);
		PacketPlayOutScoreboardScore ps5 = new PacketPlayOutScoreboardScore(s5);
		PacketPlayOutScoreboardScore ps6 = new PacketPlayOutScoreboardScore(s6);
		PacketPlayOutScoreboardScore ps7 = new PacketPlayOutScoreboardScore(s7);
		PacketPlayOutScoreboardScore ps8 = new PacketPlayOutScoreboardScore(s8);
		PacketPlayOutScoreboardScore ps9 = new PacketPlayOutScoreboardScore(s9);
		PacketPlayOutScoreboardScore ps10 = new PacketPlayOutScoreboardScore(s10);
		PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
		PacketPlayOutScoreboardScore ps12 = new PacketPlayOutScoreboardScore(s12);
		
		sendPackets(p, remove);
		sendPackets(p, create);
		sendPackets(p, display);
		
		sendPackets(p, ps0);
		sendPackets(p, ps1);
		sendPackets(p, ps2);
		sendPackets(p, ps3);
		sendPackets(p, ps4);
		sendPackets(p, ps5);
		sendPackets(p, ps6);
		sendPackets(p, ps7);
		sendPackets(p, ps8);
		sendPackets(p, ps9);
		sendPackets(p, ps10);
		sendPackets(p, ps11);
		sendPackets(p, ps12);
	}
}
