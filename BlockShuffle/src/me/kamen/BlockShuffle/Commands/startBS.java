package me.kamen.BlockShuffle.Commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitScheduler;

import me.kamen.BlockShuffle.Main;

public class startBS implements CommandExecutor{

	private Main plugin;
	
	public startBS(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("startBS").setExecutor(this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		Player p = (Player) sender;
		
		BukkitScheduler sched = p.getServer().getScheduler();
		Material[] mats = Material.values();
		Random rand = new Random();
		
		for(int i = 0; i < args.length; i++) {
			
			this.plugin.plmap.put(args[i], null);
			
		}
		
		
		p.getServer().broadcastMessage("Block Shuffle starting!!!");
		
			
			sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
				
				//int task1;
				final int RoundTime = 300;
				Material mat;
				boolean end = false;
				boolean isStarting = true;
				boolean isEnding = false;
				int numberofPlayersFoundBlocks = 0;
				int cnt = 10;
				int time = RoundTime;
				
				@Override
				public void run() {
					
					if(isStarting) {
						
						if(cnt == 0) {
							cnt = 10;
							time = RoundTime;
							isStarting = false;
							for(Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
								
										
										mat = mats[rand.nextInt(mats.length-1)];
										while(!mat.isSolid() || mat.name().contains("SHULKER") ||mat.name().contains("INFESTED") || mat.name().contains("SLAB") || mat.name().contains("BANNER") || mat.name().contains("DOOR") || mat.name().contains("COMMAND") || mat.name().contains("DEAD") || mat.name().contains("DIAMOND_BLOCK") || mat.name().contains("EMERALD") || mat.name().contains("END") || mat.name().contains("HEAD")) {
											mat = mats[rand.nextInt(mats.length-1)];
										}
										
										plugin.plmap.replace(entry.getKey(), mat);
										p.getServer().getPlayer(entry.getKey()).sendMessage("You have 5 minutes to find: " + mat.name());
										
									
								
							}
							
						}else {
							
							p.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + Integer.toString(cnt--));
							
						}
						
					}
					
					if(time == 10) {
						isEnding = true;
					}
					
					if(isEnding) {
						
						if(cnt == 0) {
							cnt = 10;
							time = RoundTime;
							isEnding = false;
							if(numberofPlayersFoundBlocks == 1) {
								
								
								for(Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
									
									if(entry.getValue() == null) {
										
										p.getServer().broadcastMessage(ChatColor.GREEN + "" + entry.getKey() + "" + ChatColor.GOLD + "" + ChatColor.BOLD + " WON!!!\n");
										
									}

								}
								end = true;
								p.getServer().getScheduler().cancelTasks(plugin);
								
							}else if(numberofPlayersFoundBlocks == 0) {
								
								p.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "THE GAME ENDED!!!\n" + ChatColor.RED + "Noone could find their block!");
								end = true;
								p.getServer().getScheduler().cancelTasks(plugin);
								
							}else {
								for(Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
									
									boolean hasABlock = false;
									
									if(entry.getValue() != null) {
										
										hasABlock = true;
										plugin.plmap.remove(entry.getKey());
										
									}
											
											if(!hasABlock) {
												mat = mats[rand.nextInt(mats.length-1)];
												while(!mat.isSolid() || mat.name().contains("SHULKER") ||mat.name().contains("INFESTED") || mat.name().contains("SLAB") || mat.name().contains("BANNER") || mat.name().contains("DOOR") || mat.name().contains("COMMAND") || mat.name().contains("DEAD") || mat.name().contains("DIAMOND_BLOCK") || mat.name().contains("EMERALD") || mat.name().contains("END") || mat.name().contains("HEAD")) {
													mat = mats[rand.nextInt(mats.length-1)];
												}
												
												plugin.plmap.replace(entry.getKey(), mat);
												p.getServer().getPlayer(entry.getKey()).sendMessage("You have 5 minutes to find: " + mat.name());
											}
											
										
									
								}
							}
							
						}else {
							
							p.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + Integer.toString(cnt--) + " seconds remaining!");
							
						}
						
					}
					
					
					
					if(!end) {
						
						if(!isStarting) {
							for(Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
								if(entry.getValue() == null) {
									++numberofPlayersFoundBlocks;
								}
								
							}
							
							if(numberofPlayersFoundBlocks >= plugin.plmap.entrySet().size()) {
								
								for(Map.Entry<String, Material> entry : plugin.plmap.entrySet()) {
									
									
									mat = mats[rand.nextInt(mats.length-1)];
									while(!mat.isSolid() || mat.name().contains("SHULKER") ||mat.name().contains("INFESTED") || mat.name().contains("SLAB") || mat.name().contains("BANNER") || mat.name().contains("DOOR") || mat.name().contains("COMMAND") || mat.name().contains("DEAD") || mat.name().contains("DIAMOND_BLOCK") || mat.name().contains("EMERALD") || mat.name().contains("END") || mat.name().contains("HEAD")) {
										mat = mats[rand.nextInt(mats.length-1)];
									}
									
									plugin.plmap.replace(entry.getKey(), mat);
									p.getServer().getPlayer(entry.getKey()).sendMessage("You have 5 minutes to find: " + mat.name());
								}
								
								numberofPlayersFoundBlocks = 0;
								isEnding = false;
								isStarting = false;
								time = RoundTime;
								cnt = 10;
								
							}
							
						}
						
						time--;
					}
						
					
				}
			}, 0L, 20);
			
		
		return false;
	}

}
