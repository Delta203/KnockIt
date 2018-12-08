package de.knockit.delta203.plugin.utils;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	public static ItemStack easy(ItemStack item, String name, String lore1) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> l = new ArrayList<>();
		l.add(lore1);
		meta.setLore(l);
		item.setItemMeta(meta);
		return item;
	}
}