package me.nd.fshield.list;

import org.bukkit.event.player.*;
import com.massivecraft.factions.entity.*;

import me.nd.fshield.manager.InventoryManagers;

import com.massivecraft.factions.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class CommandEvent implements Listener {
    @EventHandler
    void a(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final MPlayer p2 = MPlayer.get((Object)p);
        if (e.getMessage().startsWith("/f escudo") || e.getMessage().startsWith("/f shield")) {
            e.setCancelled(true);
            if (!p2.hasFaction()) {
                p.sendMessage("§cVocê precisa possuir uma facção.");
                return;
            }
            if (p2.getRole() == Rel.MEMBER || p2.getRole() == Rel.RECRUIT) {
                p.sendMessage("§cVocê precisa ser Capitão ou superior.");
                return;
            }
            final Player player = p;
            new InventoryManagers();
            player.openInventory(InventoryManagers.shieldMenu(p));
            p.sendMessage("§aSistema de Escudo aberto.");
        }
    }
}
