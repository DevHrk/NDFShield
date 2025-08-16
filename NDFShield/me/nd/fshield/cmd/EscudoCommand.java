package me.nd.fshield.cmd;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

import me.nd.fshield.Main;
import me.nd.fshield.manager.InventoryManagers;

import com.massivecraft.factions.entity.*;

public class EscudoCommand extends Commands {
	
	FileConfiguration m = Main.get().getConfig();
	public EscudoCommand() {
        super("escudo");
    }
	public void perform(CommandSender s, String lb, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage("§cApenas para jogadores autenticados.");
            return;
        }
        final Player p = (Player)s;
            if (args.length == 0) {
                p.sendMessage("§cIncorreto, /escudo verificar <tag>.");
                return;
            }
            if (args[0].equalsIgnoreCase("verificar")) {
            	if (!p.hasPermission(m.getString("Permissões.verificar"))) {
                    p.sendMessage("§cVocê não possui permissão.");
                    return;
                }
                if (args.length < 2) {
                    p.sendMessage("§cIncorreto, /escudo verificar <tag>.");
                    return;
                }
                
                final Faction fac = FactionColl.get().getByTag(args[1]);
                if (fac == null) {
                    p.sendMessage("§cA facção não foi encontrada!");
                    return;
                }
                final Player player = p;
                new InventoryManagers();
                player.openInventory(InventoryManagers.verifyEscudo(args[1]));
                p.sendMessage("§aVerificação de Escudo aberta.");
             
            }
        return;
    }
}
