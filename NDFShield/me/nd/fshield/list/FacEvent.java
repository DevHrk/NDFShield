package me.nd.fshield.list;

import org.bukkit.*;
import org.bukkit.event.*;
import com.massivecraft.factions.event.*;

import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.service.SQLite;

public class FacEvent implements Listener
{
    @EventHandler
    void a(final EventFactionsCreate e) {
        SQLite.createFacInDB(e.getFactionTag());
        Bukkit.getConsoleSender().sendMessage("§b[NDfshield] A facção §f" + e.getFactionTag() + " §bfoi registrada.");
    }
    
    @EventHandler
    void a(final EventFactionsDisband e) {
        ShieldAPI.deleteFac(e.getFaction().getTag());
        Bukkit.getConsoleSender().sendMessage("§b[NDfshield] §cA facção §f" + e.getFaction().getTag() + " §cfoi deletada.");
    }
}
