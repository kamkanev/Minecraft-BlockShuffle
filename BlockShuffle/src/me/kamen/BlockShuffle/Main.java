package me.kamen.BlockShuffle;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import me.kamen.BlockShuffle.Commands.startBS;
import me.kamen.BlockShuffle.Commands.stopBS;
import me.kamen.BlockShuffle.Listeners.ListenBS;

public class Main extends JavaPlugin{

	public HashMap<String, Material> plmap = new HashMap<String, Material>();
	
	@Override
	public void onEnable() {
		new startBS(this);
		new stopBS(this);
		new ListenBS(this);
	}

}
