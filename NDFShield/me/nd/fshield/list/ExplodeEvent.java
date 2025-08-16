package me.nd.fshield.list;

import org.bukkit.event.entity.*;
import com.massivecraft.massivecore.ps.*;

import me.nd.fshield.Main;
import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.utils.ActionBarAPI;

import org.bukkit.configuration.file.*;
import org.bukkit.event.*;
import com.massivecraft.factions.entity.*;

public class ExplodeEvent implements Listener
{
	
    @EventHandler
    void a(final EntityExplodeEvent e) {
        final Faction ps = BoardColl.get().getFactionAt(PS.valueOf(e.getLocation()));
        FileConfiguration config = Main.get().getConfig();
        if (ShieldAPI.getEscudo(ps.getTag())) {
        e.setCancelled(true);
        ps.getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer(), config.getString("Mensagens.TentandoAtaque").replace("&", "§")));
        }
        }
}
