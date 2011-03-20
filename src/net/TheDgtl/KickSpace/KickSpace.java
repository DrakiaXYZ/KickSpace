package net.TheDgtl.KickSpace;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KickSpace extends JavaPlugin {
	private final pListener playerListener = new pListener();
	private Logger log;
	private PluginManager pm;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
    	log = Logger.getLogger("Minecraft");
    	
        log.info(pdfFile.getName() + " v." + pdfFile.getVersion() + " is enabled.");
        
        pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Priority.Highest, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private class pListener extends PlayerListener {
		@Override
		public void onPlayerLogin(PlayerLoginEvent event) {
			String name = event.getPlayer().getName();
			if (name.contains(" ")) {
				event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Can not login with a space in your name");
			}
		}
	}
}
