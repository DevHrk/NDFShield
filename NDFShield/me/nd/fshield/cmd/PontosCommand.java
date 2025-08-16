package me.nd.fshield.cmd;

import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

import me.nd.fshield.Main;
import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.utils.FormatterShield;

import com.massivecraft.factions.entity.*;

public class PontosCommand extends Commands{
	FileConfiguration m = Main.get().getConfig();
	public PontosCommand() {
        super("pontos");
    }
	public void perform(CommandSender s, String lb, String[] args) {
        final Player p = (Player)s;
            if (args.length == 0) {
                p.sendMessage("§cUtilize, /pontos ajuda.");
                return;
            }
            if (args[0].equalsIgnoreCase("ajuda")) {
                if (!p.hasPermission(m.getString("Permissões.pontos"))) {
                    p.sendMessage("§cVocê não possui permissão.");
                    return;
                }
                	p.sendMessage("");
                	p.sendMessage("§e/pontos ver <tag> - ver pontos da facção.");
                    p.sendMessage("§e/pontos adicionar <tag> <quantia> - adiciona pontos a facção.");
                    p.sendMessage("§e/pontos remover <tag> <quantia> - remove pontos da facção.");
                    p.sendMessage("");
                    return;
                }
            
            if (args[0].equalsIgnoreCase("adicionar")) {
            	  if (!p.hasPermission(m.getString("Permissões.pontos"))) {
                    p.sendMessage("§cVocê não possui permissão.");
                    return;
                }
                if (args.length < 2) {
                    p.sendMessage("§cIncorreto, /pontos adicionar <tag> <quantia>.");
                    return;
                }
                final Faction fac = FactionColl.get().getByTag(args[1]);
                Double quantia = null;
                if (fac == null) {
                    p.sendMessage("§cA facção não foi encontrada!");
                    return;
                }
                try {
                    quantia = Double.parseDouble(args[2]);
                }
                catch (NumberFormatException e) {
                    p.sendMessage("§cInsira apenas numeros.");
                    return;
                }
                ShieldAPI.addPontos(fac.getTag(), quantia);
                p.sendMessage("§aYeah! Pontos adicionados com sucesso.");
            }
    
            if (args[0].equalsIgnoreCase("ver")) {
            	  if (!p.hasPermission(m.getString("Permissões.pontos-ver"))) {
                    p.sendMessage("§cVocê não possui permissão.");
                    return;
                }
                if (args.length < 2) {
                    p.sendMessage("§cIncorreto, /pontos ver <tag>.");
                    return;
                }
                final Faction fac = FactionColl.get().getByTag(args[1]);
                if (fac == null) {
                    p.sendMessage("§cA facção não foi encontrada!");
                    return;
                } 
                final MPlayer p2 = MPlayer.get((Object)p);
                p.sendMessage("§aA facção " + p2.getFactionTag() + " §apossui " +  FormatterShield.formatNumber(ShieldAPI.getPoints(fac.getTag())) + " §apontos" );
            }
            
            if (args[0].equalsIgnoreCase("remover")) {
            	  if (!p.hasPermission(m.getString("Permissões.pontos"))) {
                    p.sendMessage("§cVocê não possui permissão.");
                    return;
                }
                if (args.length < 2) {
                    p.sendMessage("§cIncorreto, /pontos remover <tag> <quantia>.");
                    return;
                }
                final Faction fac = FactionColl.get().getByTag(args[1]);
                Double quantia = null;
                if (fac == null) {
                    p.sendMessage("§cA facção não foi encontrada!");
                    return;
                }
                try {
                    quantia = Double.parseDouble(args[2]);
                }
                catch (NumberFormatException e) {
                    p.sendMessage("§cInsira apenas numeros.");
                    return;
                }
                if (ShieldAPI.getPoints(fac.getTag()) < quantia) {
                    p.sendMessage("§cVocê inseriu um valor maior que a facção possui.");
                    return;
                }
                ShieldAPI.removePontos(fac.getTag(), quantia);
                p.sendMessage("§aYeah! Pontos adicionados com sucesso.");
            }
        return;
    }
}
