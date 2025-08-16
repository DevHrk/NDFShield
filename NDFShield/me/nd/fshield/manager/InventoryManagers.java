package me.nd.fshield.manager;

import java.text.*;
import org.bukkit.entity.*;
import com.massivecraft.factions.util.Banner;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.*;

import me.nd.fshield.Main;
import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.api.SkullAPI;
import me.nd.fshield.utils.FormatterShield;

import org.bukkit.configuration.file.*;
import com.massivecraft.factions.entity.*;
import java.util.concurrent.*;
import java.util.*;

public class InventoryManagers
{
    public static final DecimalFormatSymbols DFS;
    public static final DecimalFormat FORMATTER;
    public static long time2;
    
    public static Inventory shieldMenu(final Player p) {
        final MPlayer p2 = MPlayer.get((Object)p);
        final Faction fac = p2.getFaction();
        FileConfiguration m = Main.get().getConfig();
        ArrayList<String> lore = new ArrayList<>();
        ArrayList<String> lore1 = new ArrayList<>();
        ArrayList<String> lore2 = new ArrayList<>();
        ArrayList<String> lore3 = new ArrayList<>();
        ArrayList<String> lore4 = new ArrayList<>();
        
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, m.getInt("Menu.principal.tamanho"), m.getString("Menu.principal.nome-menu").replace("&", "§").replace("{tag}", p2.getFactionTag()));
        	 
        
	    ItemStack qqq = new ItemStack(fac.isInAttack() ? Banner.getRedBanner(p2.getFactionTag().toLowerCase()) : Banner.getWhiteBanner(p2.getFactionTag().toLowerCase()));
	    ItemMeta www = qqq.getItemMeta();
	    for (String lorezq : m.getStringList("Menu.principal.faccao.lore")) {
	    	lore.add(lorezq.replace("&", "§").replace("{poder}", fac.getPowerRounded() + "/" + fac.getPowerMaxRounded()).replace("{pontos}", FormatterShield.formatNumber(ShieldAPI.getPoints(fac.getTag())).replace("{terras}", fac.getLandCount() + "").replace("{lider}", fac.getLeader() + "")));
        www.setDisplayName(m.getString("Menu.principal.faccao.name").replace("&", "§").replace("{tag}", p2.getFactionTag()).replace("{faccao}", p2.getFactionName()));
        www.setLore(lore);
	    qqq.setItemMeta(www);
	    inv.setItem(m.getInt("Menu.principal.faccao.Slot"), qqq);
	    }
	    
		  if (m.getString("Menu.principal.top.type").equalsIgnoreCase("MATERIAL")) {
			    ItemStack ab1 = new ItemStack(Material.getMaterial(m.getString("Menu.principal.top.material")));
			    ItemMeta ma2 = ab1.getItemMeta();
			    for (String lorez1223 : m.getStringList("Menu.principal.top.lore")) {
			    	lore4.add(lorez1223.replace("&", "§"));
			    ma2.setDisplayName(m.getString("Menu.principal.top.name").replace("&", "§"));
			    ma2.setLore(lore4);
			    ab1.setItemMeta(ma2);
			    inv.setItem(m.getInt("Menu.principal.top.Slot"), ab1);
			     }
			   }
				  
			    if (m.getString("Menu.principal.top.type").equalsIgnoreCase("HEAD")) {
			    ItemStack ab2 = new ItemStack(SkullAPI.getSkull(m.getString("Menu.principal.top.head")));
			    ItemMeta ma1 = ab2.getItemMeta();
			    for (String lorez1223 : m.getStringList("Menu.principal.top.lore")) {
			    	lore4.add(lorez1223.replace("&", "§"));
			    ma1.setDisplayName(m.getString("Menu.principal.top.name").replace("&", "§"));
			    ma1.setLore(lore4);
			    ab2.setItemMeta(ma1);
			    inv.setItem(m.getInt("Menu.principal.top.Slot"), ab2);
			     }
			    }
	    
			  if (m.getString("Menu.principal.melhorias.type").equalsIgnoreCase("MATERIAL")) {
		    ItemStack ab = new ItemStack(Material.getMaterial(m.getString("Menu.principal.melhorias.material")));
		    ItemMeta ma = ab.getItemMeta();
		    for (String lorez : m.getStringList("Menu.principal.melhorias.lore")) {
		    	lore1.add(lorez.replace("&", "§"));
		    ma.setDisplayName(m.getString("Menu.principal.melhorias.name").replace("&", "§"));
		    ma.setLore(lore1);
		    ab.setItemMeta(ma);
		    inv.setItem(m.getInt("Menu.principal.melhorias.Slot"), ab);
		     }
		   }
			  
		    if (m.getString("Menu.principal.melhorias.type").equalsIgnoreCase("HEAD")) {
		    ItemStack ab = new ItemStack(SkullAPI.getSkull(m.getString("Menu.principal.melhorias.head")));
		    ItemMeta ma = ab.getItemMeta();
		    for (String lorez : m.getStringList("Menu.principal.melhorias.lore")) {
		    	lore1.add(lorez.replace("&", "§"));
		    ma.setDisplayName(m.getString("Menu.principal.melhorias.name").replace("&", "§"));
		    ma.setLore(lore1);
		    ab.setItemMeta(ma);
		    inv.setItem(m.getInt("Menu.principal.melhorias.Slot"), ab);
		     }
		    }	 
		    
		    if (m.getString("Menu.principal.escudoComprar.type").equalsIgnoreCase("MATERIAL")) {
		    ItemStack cc = new ItemStack(Material.getMaterial(m.getString("Menu.principal.escudoComprar.material")));
		    ItemMeta dd = cc.getItemMeta();
		    for (String lore8 : m.getStringList("Menu.principal.escudoComprar.lore")) {
		    	lore2.add(lore8.replace("&", "§").replace("{status}", ShieldAPI.getEscudo(fac.getTag()) ? "§aSua facção ja possui um Escudo ativado." : "§7Clique para adquirir um Escudo."));
		    dd.setDisplayName(m.getString("Menu.principal.escudoComprar.name").replace("&", "§"));
		    dd.setLore(lore2);
		    cc.setItemMeta(dd);
		    inv.setItem(m.getInt("Menu.principal.escudoComprar.Slot"), cc);
		     }
		    }
		   
		    if (m.getString("Menu.principal.escudoComprar.type").equalsIgnoreCase("HEAD")) {
		    ItemStack cc = new ItemStack(SkullAPI.getSkull(m.getString("Menu.principal.escudoComprar.head")));
		    ItemMeta dd = cc.getItemMeta();
		    for (String lore8 : m.getStringList("Menu.principal.escudoComprar.lore")) {
		    	lore2.add(lore8.replace("&", "§").replace("{status}", ShieldAPI.getEscudo(fac.getTag()) ? "§aSua facção ja possui um Escudo ativado." : "§7Clique para adquirir um Escudo."));
		    dd.setDisplayName(m.getString("Menu.principal.escudoComprar.name").replace("&", "§"));
		    dd.setLore(lore2);
		    cc.setItemMeta(dd);
		    inv.setItem(m.getInt("Menu.principal.escudoComprar.Slot"), cc);
		     }
		    }
		    
		    if (m.getString("Menu.principal.escudoStatus.type").equalsIgnoreCase("MATERIAL")) {
		    ItemStack ee = new ItemStack(Material.getMaterial(m.getString("Menu.principal.escudoStatus.material")));
		    ItemMeta bb = ee.getItemMeta();
		    for (String lore18 : m.getStringList("Menu.principal.escudoStatus.lore")) {
		    lore3.add(lore18.replace("&", "§").replace("{status}", ShieldAPI.getEscudo(fac.getTag()) ? "§aHabilitado" : "§cDesabilitado").replace("{duracao}", ShieldAPI.getEscudo(fac.getTag()) ? ("§fDuração: §c" + InventoryManagers.timeFormatter(fac.getTag(), InventoryManagers.time2)) : "§cCompre um Escudo para ver a duração."));
		    bb.setDisplayName(m.getString("Menu.principal.escudoStatus.name").replace("&", "§"));   
		    bb.setLore(lore3);
		    ee.setItemMeta(bb);
		    inv.setItem(m.getInt("Menu.principal.escudoStatus.Slot"), ee);
		      }
		    }
		    
		    if (m.getString("Menu.principal.escudoStatus.type").equalsIgnoreCase("HEAD")) {
		    ItemStack ee = new ItemStack(SkullAPI.getSkull(m.getString("Menu.principal.escudoStatus.head")));
		    ItemMeta bb = ee.getItemMeta();
		    for (String lore18 : m.getStringList("Menu.principal.escudoStatus.lore")) {
		    lore3.add(lore18.replace("&", "§").replace("{status}", ShieldAPI.getEscudo(fac.getTag()) ? "§aHabilitado" : "§cDesabilitado").replace("{duracao}", ShieldAPI.getEscudo(fac.getTag()) ? ("§fDuração: §c" + InventoryManagers.timeFormatter(fac.getTag(), InventoryManagers.time2)) : "§cCompre um Escudo para ver a duração."));
		    bb.setDisplayName(m.getString("Menu.principal.escudoStatus.name").replace("&", "§"));   
		    bb.setLore(lore3);
		    ee.setItemMeta(bb);
		    inv.setItem(m.getInt("Menu.principal.escudoStatus.Slot"), ee);
		      } 
		  } 
		    
	    return inv;
    }
    
    public static Inventory viewUpgradesMenu(final Player p) {
        final MPlayer p2 = MPlayer.get((Object)p);
        final Faction fac = p2.getFaction();
        FileConfiguration config = Main.get().getConfig();
        ArrayList<String> lore31 = new ArrayList<>();
        ArrayList<String> lore31q = new ArrayList<>();
        ArrayList<String> lore311q = new ArrayList<>();
        
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, config.getInt("Menu.Upgrades.tamanho"), config.getString("Menu.Upgrades.name").replace("&", "§").replace("{tag}", p2.getFactionTag()));      
	    ItemStack ee = new ItemStack(Material.INK_SACK, 1, (byte)((ShieldAPI.getNivel(fac.getTag()) >= config.getInt("Upgrades.NivelI.minutosGanhos")) ? 10 : 8));
	    ItemMeta bb = ee.getItemMeta();
	    for (String lore181 : config.getStringList("Menu.Upgrades.NivelI.lore")) {
	    lore31.add(lore181.replace("&", "§").replace("{status}", ShieldAPI.getNivel(fac.getTag()) >= Integer.valueOf(config.getInt("Upgrades.NivelI.minutosGanhos")) ? "§aSua facção ja possui esse upgrade." : ("§7Custo: §f" + FormatterShield.formatNumber(config.getInt("Upgrades.NivelI.preco")) + " Pontos")));   
        bb.setDisplayName(config.getString("Menu.Upgrades.NivelI.name").replace("&", "§"));
        bb.setLore(lore31);
	    ee.setItemMeta(bb);
	    inv.setItem(config.getInt("Menu.Upgrades.NivelI.Slot"), ee);
	    }
	    ItemStack eeq = new ItemStack(Material.INK_SACK, 1, (byte)((ShieldAPI.getNivel(fac.getTag()) >= config.getInt("Upgrades.NivelII.minutosGanhos")) ? 10 : 8));
	    ItemMeta bbq = eeq.getItemMeta();
	    for (String lore181q : config.getStringList("Menu.Upgrades.NivelII.lore")) {
	    lore31q.add(lore181q.replace("&", "§").replace("{status}", ShieldAPI.getNivel(fac.getTag()) >= Integer.valueOf(config.getInt("Upgrades.NivelII.minutosGanhos")) ? "§aSua facção ja possui esse upgrade." : ("§7Custo: §f" + FormatterShield.formatNumber(config.getInt("Upgrades.NivelII.preco")) + " Pontos")));   
	    bbq.setDisplayName(config.getString("Menu.Upgrades.NivelII.name").replace("&", "§"));
        bbq.setLore(lore31q);
	    eeq.setItemMeta(bbq);
	    inv.setItem(config.getInt("Menu.Upgrades.NivelII.Slot"), eeq);
	    }
	    ItemStack eeq1 = new ItemStack(Material.INK_SACK, 1, (byte)((ShieldAPI.getNivel(fac.getTag()) >= config.getInt("Upgrades.NivelIII.minutosGanhos")) ? 10 : 8));
	    ItemMeta bbq1 = eeq.getItemMeta();
	    for (String lore1811q : config.getStringList("Menu.Upgrades.NivelIII.lore")) {
	  	lore311q.add(lore1811q.replace("&", "§").replace("{status}", ShieldAPI.getNivel(fac.getTag()) >= Integer.valueOf(config.getInt("Upgrades.NivelIII.minutosGanhos")) ? "§aSua facção ja possui esse upgrade." : ("§7Custo: §f" + FormatterShield.formatNumber(config.getInt("Upgrades.NivelIII.preco")) + " Pontos")));   
        bbq1.setDisplayName(config.getString("Menu.Upgrades.NivelIII.name").replace("&", "§"));
        bbq1.setLore(lore311q);
	    eeq1.setItemMeta(bbq1);
	    inv.setItem(config.getInt("Menu.Upgrades.NivelIII.Slot"), eeq1);
	    }  
	    return inv;
    }
    
    public static Inventory verifyEscudo(final String tag) {
        final Faction fac = FactionColl.get().getByTag(tag);
        FileConfiguration m = Main.get().getConfig();
        ArrayList<String> lore99 = new ArrayList<>();
        
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, m.getInt("Menu.Verificar.tamanho"), m.getString("Menu.Verificar.name").replace("&", "§"));
   	    ItemStack ll = new ItemStack(Banner.getWhiteBanner(tag.toLowerCase()));
	    ItemMeta mp = ll.getItemMeta();
	    for (String lores222 : m.getStringList("Menu.Verificar.menu.lore")) {
	    	lore99.add(lores222.replace("&", "§").replace("{status}", ShieldAPI.getEscudo(fac.getTag()) ? "§aHabilitado" : "§cDesabilitado"));
        mp.setDisplayName(m.getString("Menu.Verificar.menu.name").replace("&", "§").replace("{tag}", tag.toUpperCase()).replace("{nome}", fac.getName()));
        mp.setLore(lore99);
	    ll.setItemMeta(mp);
	    inv.setItem(m.getInt("Menu.Verificar.menu.Slot"), ll);
	    }
        
        return inv;
    }
    
    public static String timeFormatter(final String tag, long time) {
        if (time == 0L) {
            return null;
        }
        FileConfiguration config = Main.get().getConfig();
        time -= System.currentTimeMillis();
        time += TimeUnit.MINUTES.toMillis(config.getInt("Escudo.Duracao") + ShieldAPI.getNivel(tag));
        final long day = TimeUnit.MILLISECONDS.toDays(time);
        final long hours = TimeUnit.MILLISECONDS.toHours(time) - day * 24L;
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.MILLISECONDS.toHours(time) * 60L;
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MILLISECONDS.toMinutes(time) * 60L;
        final StringBuilder stringBuilder = new StringBuilder();
        if (day > 0L) {
            stringBuilder.append(day).append(" dias").append(" ");
        }
        if (hours > 0L) {
            stringBuilder.append(hours).append(" horas").append(" ");
        }
        if (minutes > 0L) {
            stringBuilder.append(minutes).append(" minutos").append(" ");
        }
        if (seconds > 0L) {
            stringBuilder.append(seconds).append(" segundos");
        }
        return stringBuilder.toString().isEmpty() ? null : stringBuilder.toString();
    }
    
    static {
        DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
        FORMATTER = new DecimalFormat("#,##0.0", InventoryManagers.DFS);
        InventoryManagers.time2 = System.currentTimeMillis();
    }
}
