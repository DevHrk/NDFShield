package me.nd.fshield;

import org.bukkit.plugin.java.*;

import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.cmd.Commands;
import me.nd.fshield.listener.Listeners;
import me.nd.fshield.service.SQLite;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin {
	
    public void onEnable() {
        saveDefaultConfig();
        SQLite.openConnection();
        ShieldAPI.setupFactions();
        this.setupEconomy();
        Listeners.setupListeners();
        Commands.setupCommands();
        Bukkit.getConsoleSender().sendMessage("§b[NDFshield] Plugin iniciado");
      }
    
    public void onDisable() {
        SQLite.closeConnection();
    }
    
	private boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (rsp == null) {
            return false;
        }
        this.econ = (Economy)rsp.getProvider();
        return this.econ != null;
    }
    
    public Economy getEconomy() {
        return this.econ;
    }
    
    
	public static Main get() {
        return (Main)JavaPlugin.getPlugin((Class)Main.class);
    } 
    
	private Economy econ;
}
