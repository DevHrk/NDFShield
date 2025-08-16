package me.nd.fshield.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.nd.fshield.Main;
import me.nd.fshield.list.*;

public class Listeners {
	public static void setupListeners() {
	    PluginManager pm = Bukkit.getPluginManager();
	    EntityEvent.loadMobPoints();
	    Listener[] listeners = {
	        new ClickEvent(),
	        new EntityEvent(),
	        new FacEvent(),
	        new CommandEvent(),
	        new ExplodeEvent(),
	    };

	    for (Listener listener : listeners) {
	        try {
	            pm.getClass().getDeclaredMethod("registerEvents", Listener.class, Plugin.class).invoke(pm, listener, Main.get());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
