package me.zackpollard.chestkits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChestKits extends JavaPlugin{
	
	public Boolean chestreset = false;
	
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public ArrayList<String> openedchests= new ArrayList<String>();
	
	public void onDisable() {
		this.logger.info("ChestKits is now disabled.");
	}
	public void onEnable() {
		
		new MyPlayerListener(this);
		new MyBlockListener(this);
		
		final FileConfiguration config = this.getConfig();
        config.options().header("Here is where you can create kits :)");
        
        String[] kit1 = {"268:0", "298:0", "299:0", "300:0", "301:0"};
        config.addDefault("ChestKits.kit1", Arrays.asList(kit1));
        
        String[] kit2 = {"268:0", "298:0", "299:0", "300:0", "301:0"};
        config.addDefault("ChestKits.kit2", Arrays.asList(kit2));
        
        String[] kit3 = {"268:0", "298:0", "299:0", "300:0", "301:0"};
        config.addDefault("ChestKits.kit3", Arrays.asList(kit3));
        
        config.options().copyDefaults(true);
        saveConfig();
		this.logger.info("ChestKits is now enabled.");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
        if(label.equalsIgnoreCase("chestkits")){

		if(sender instanceof Player || sender instanceof ConsoleCommandSender){
			if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
				
                if(sender.hasPermission("chestkits.reload")){
                	
                	sender.sendMessage(ChatColor.GOLD + "ChestKits config reloaded.");
       	    	    this.reloadConfig();
                    logger.info("Reloaded Config File");
                    
                }        
                return true;
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
				
				for(String list : openedchests){
					
					sender.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.GREEN + "Chest reset at " + list);
					
				}
				
				this.openedchests.clear();
				
				sender.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.GREEN + "Chests Reset Succesfully :)");
				
				return true;
			}
			
			if(args.length == 2 && args[0].equalsIgnoreCase("reset")){
				Integer i = 0;
				Integer j = 0;
				Integer k = 0;
				Integer area = Integer.parseInt(args[1]);
				
				if(sender instanceof Player){
	        		Player player = (Player) sender;
	        		
	        		Integer x = player.getLocation().getBlockX();
	        		Integer y = player.getLocation().getBlockY();
	        		Integer z = player.getLocation().getBlockZ();
	        		
					for (i = area * -1; i <= area; i++){
					    for (j = area * -1; j <= area; j++) {
					        for (k = area * -1; k <= area; k++) {
					        	
				        		String location = (x + i) + "," + (y + j) + "," + (z + k);
					        		
						            if(openedchests.contains(location)){
						            	
						            	openedchests.remove(location);
						            		
						            	sender.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.GREEN + "Chest reset at " + location);
						            }
						        }
						    }
						}
					}		
				}
				return true;
			}
		}
		return true;
	}
}