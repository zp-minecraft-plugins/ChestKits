package me.zackpollard.chestkits;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class MyBlockListener implements Listener{
	public static ChestKits plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public MyBlockListener(ChestKits instance){
		plugin = instance;
		Bukkit.getServer().getPluginManager().registerEvents(this,instance);
	}
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(block.getType().equals(Material.CHEST)){
			
       		if(block.getRelative(0, 1, 0).getType().equals(Material.WALL_SIGN)){
       			
       			Sign sign = (Sign) block.getRelative(0, 1, 0).getState();
       			
       			if(sign.getLine(0).equalsIgnoreCase("[ChestKits]")){
       				
       				if(!player.hasPermission("chestkits.chest.place")){
       					
       					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
       					
       					event.setCancelled(true);
       					
       				}
       			}
       		}
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event){
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(block.getType().equals(Material.CHEST)){
			
			if(block.getRelative(0, 1, 0).getType().equals(Material.WALL_SIGN)){
       			
       			Sign sign = (Sign) block.getRelative(0, 1, 0).getState();
       			
       			if(sign.getLine(0).equalsIgnoreCase("[ChestKits]")){
       				
       				if(!player.hasPermission("chestkits.chest.remove")){
       					
       					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
       					
       					event.setCancelled(true);
       					
       				}
       			}
			}
		}
		
		if(block.getType().equals(Material.WALL_SIGN)){
			
			Sign sign = (Sign) event.getBlock().getState();
			
			if(sign.getLine(0).equalsIgnoreCase("[ChestKits]")){
				
				if(!player.hasPermission("chestkits.sign.remove")){
					
					if(!player.hasPermission("chestkits.sign.remove")){
					
						player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
						
						event.setCancelled(true);
					
					}
				}
			}
		}
		
		if(block.getRelative(1, 0, 0).getType().equals(Material.WALL_SIGN)){
			
			Sign sign1 = (Sign) block.getRelative(1, 0, 0).getState();
			
			if(sign1.getLine(0).equalsIgnoreCase("[ChestKits]")){
				
				if(!player.hasPermission("chestkits.sign.remove")){
					
					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
					
					event.setCancelled(true);
				
				}
				
			}
			
		}
		
		if(block.getRelative(-1, 0, 0).getType().equals(Material.WALL_SIGN)){
			
			Sign sign2 = (Sign) block.getRelative(-1, 0, 0).getState();
			
			if(sign2.getLine(0).equalsIgnoreCase("[ChestKits]")){
				
				if(!player.hasPermission("chestkits.sign.remove")){
					
					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
					
					event.setCancelled(true);
				
				}
				
			}
			
		}
		
		if(block.getRelative(0, 0, 1).getType().equals(Material.WALL_SIGN)){
			
			Sign sign3 = (Sign) block.getRelative(0, 0, 1).getState();
			
			if(sign3.getLine(0).equalsIgnoreCase("[ChestKits]")){
				
				if(!player.hasPermission("chestkits.sign.remove")){
					
					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
					
					event.setCancelled(true);
				
				}
				
			}
			
		}
		
		if(block.getRelative(0, 0, -1).getType().equals(Material.WALL_SIGN)){
			
			Sign sign4 = (Sign) block.getRelative(0, 0, -1).getState();
			
			if(sign4.getLine(0).equalsIgnoreCase("[ChestKits]")){
				
				if(!player.hasPermission("chestkits.sign.remove")){
					
					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
					
					event.setCancelled(true);
				
				}
			}
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onSignChange(SignChangeEvent event){
		
		Player player = event.getPlayer();
		
		if(event.getLine(0).equalsIgnoreCase("[ChestKits]")){
			
			if(!player.hasPermission("chestkits.sign.place")){
			
				player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
				
				event.setCancelled(true);
			
			}
			
			if(event.getLine(3).equalsIgnoreCase("Constant")){
				
				if(!player.hasPermission("chestkits.sign.constant")){
					
					player.sendMessage(ChatColor.GOLD + "[ChestKits] " + ChatColor.RED + "You don't have permission to do this!");
					
					event.setCancelled(true);
					
				}
			}
		}
	}
}