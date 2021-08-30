package me.kamen.BlockShuffle.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.kamen.BlockShuffle.Main;

public class ListenBS implements Listener{
	
	private Main plugin;

	public ListenBS(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void moveEvent(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		Material pmat = this.plugin.plmap.get(p.getName());
		Material bmat = e.getTo().clone().subtract(0, 1, 0).getBlock().getType();
		
		if(bmat.equals(pmat)) {
			this.plugin.plmap.replace(p.getName(), null);
			
			p.getServer().broadcastMessage(p.getName() + " found " + bmat.name());
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		}
		
	}

}
