package me.zackpollard.chestkits;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MyPlayerListener implements Listener{
	public static ChestKits plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public MyPlayerListener(ChestKits instance){
		plugin = instance;
		Bukkit.getServer().getPluginManager().registerEvents(this,instance);
	}
	@EventHandler(ignoreCancelled = true)
	public void onInventoryOpen(InventoryOpenEvent event) {
		
		Integer quantity = 1;
       	
		if(event.getView().getType().equals(InventoryType.CHEST)){
       		
       		Inventory inventory = event.getInventory();
       		Chest chest = (Chest) inventory.getHolder();
       		Block block = (Block) chest.getBlock();
       		String location = block.getX() + "," + block.getY() + "," + block.getZ();
       		
       		if(block.getRelative(0, 1, 0).getType().equals(Material.WALL_SIGN)){
       			
       			Sign sign = (Sign) block.getRelative(0, 1, 0).getState();
       			
       			if(sign.getLine(0).equalsIgnoreCase("[ChestKits]")){
       				
       				if(!sign.getLine(1).isEmpty() && sign.getLine(1).contains("kit")){
       					
       					if(sign.getLine(2).isEmpty()){
       						
       						quantity = 1;
       						
       					} else {
       						
       						quantity = Integer.parseInt(sign.getLine(2).toString());
       						
       					}
       					
       					String kit = sign.getLine(1).toString().toLowerCase();
       					
       					List<String> kitcontents = plugin.getConfig().getStringList("ChestKits." + kit);
   							
						if(!sign.getLine(3).equalsIgnoreCase("Constant")){
				
	   						if(!plugin.openedchests.contains(location)){
	   							
	   							inventory.clear();
	   							
		       					for (String item : kitcontents){
		   							
		       						String[] split = item.split(":");
		       					    Integer itemid = Integer.parseInt(split[0]);
		       					    Byte datavalue = Byte.parseByte(split[1]);
		       					    ItemStack itemstack = new ItemStack(itemid, quantity, datavalue);
		       					    
		       					    inventory.addItem(itemstack);
		
		       					}
		       					
		       					plugin.openedchests.add(location);
		       					
	   						} else {
	   							
	   							if(plugin.openedchests.contains(location)){
	   								
	   								return;
	   								
	   							}
	   							
	   						}
   							
						} else {
							
							if(sign.getLine(3).equalsIgnoreCase("Constant")){
								
								inventory.clear();
								
		       					for (String item : kitcontents){
		   							
		       						String[] split = item.split(":");
		       					    Integer itemid = Integer.parseInt(split[0]);
		       					    Short durability = Short.parseShort(split[1]);
		       					    ItemStack itemstack = new ItemStack(itemid, quantity, durability);
		       					    
		       					    inventory.addItem(itemstack);
		
		       					}
		       					
		       					if(!plugin.openedchests.contains(location)){
		       					
		       						plugin.openedchests.add(location);
		       					
		       					}
		       					return;
		       				}
						}
					}
   				}
   			}
   		}
   	}
}