package me.nd.fshield.list;

import java.text.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import com.massivecraft.factions.entity.*;

import me.nd.fshield.Main;
import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.manager.InventoryManagers;
import me.nd.fshield.manager.Ranking;
import me.nd.fshield.utils.ActionBarAPI;
import me.nd.fshield.utils.TitleAPI;

import org.bukkit.*;
import org.bukkit.scheduler.*;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import java.util.*;

public class ClickEvent implements Listener
{
    public static DecimalFormatSymbols DFS;
    public static DecimalFormat FORMATTER;
    
    @EventHandler
    public void b(final InventoryClickEvent e) {
    	
        final Player p = (Player)e.getWhoClicked();
        final MPlayer p2 = MPlayer.get((Object)p);
        final ItemStack item = e.getCurrentItem();
        FileConfiguration config = Main.get().getConfig();
        
            if (e.getInventory().getName().equalsIgnoreCase(config.getString("Menu.principal.nome-menu").replace("&", "§").replace("{tag}", p2.getFactionTag()))) {
                e.setCancelled(true);

               if (config.getBoolean("Utils.Pontos")) {      
                	if (e.getSlot() == config.getInt("Menu.principal.melhorias.Slot")) {
                        p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                	}    
                    	if (e.getSlot() == config.getInt("Menu.principal.top.Slot")) {
                    		  Ranking.open(p);
                    	}
                	
                    else if (e.getSlot() == config.getInt("Menu.principal.escudoComprar.Slot")) {
                        if (ShieldAPI.getEscudo(p2.getFactionTag())) {
                            p.sendMessage("§cSua facção ja possui um Escudo ativo.");
                            return;
                        }
                        
                        if (ShieldAPI.getPoints(p2.getFactionTag()) < config.getInt("Escudo.ComprarEscudo")) {
                            p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getInt("Escudo.ComprarEscudo")) + " §cPontos para comprar isto.");
                            return;
                        }             
                        
                        if (config.getString("Utils.compra").equalsIgnoreCase("COMPRA")) {
                        p2.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer(), config.getString("Mensagens.EscudoComprado").replace("&", "§").replace("{tag}", p2.getFactionTag())));               	         
                        ShieldAPI.removePontos(p2.getFactionTag(), config.getInt("Escudo.ComprarEscudo"));
                        ShieldAPI.setEscudo(p2.getFactionTag(), 1);
                        p.closeInventory();
                        p.openInventory(InventoryManagers.shieldMenu(p));
                        }

                        if (config.getString("Utils.compra").equalsIgnoreCase("ANUNCIAR")) {
                        	 TitleAPI.sendTitle(p, 20, 30, 20, config.getString("Mensagens.EscudoAnunciar-Tittle").replace("&", "§").replace("{tag}", p2.getFactionTag()), config.getString("Mensagens.EscudoAnunciar-SubTittle").replace("&", "§").replace("{tag}", p2.getFactionTag()));               	         
                             ShieldAPI.removePontos(p2.getFactionTag(), config.getInt("Escudo.ComprarEscudo"));
                             ShieldAPI.setEscudo(p2.getFactionTag(), 1);
                             p.closeInventory();
                             p.openInventory(InventoryManagers.shieldMenu(p));
                        }
                        final int time = config.getInt("Escudo.Duracao") + ShieldAPI.getNivel(p2.getFactionTag());
                        new BukkitRunnable() {
                            public void run() {
                                ShieldAPI.setEscudo(p2.getFactionTag(), 0);
                                p2.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer(), config.getString("Mensagens.EscudoTerminou").replace("&", "§").replace("{tag}", p2.getFactionTag())));
                            }
                        }.runTaskLater((Plugin)Main.get(), time * 20L);
                    }
               }
           } else if (e.getInventory().getTitle().equals(config.getString("Menu.Upgrades.name").replace("&", "§").replace("{tag}", p2.getFactionTag()))) {
                e.setCancelled(true);
              if (config.getBoolean("Utils.Pontos")) {    
                if (e.getClickedInventory() != null && item != null && item.getType() != Material.AIR) {
                    if (e.getSlot() == 12) {
                    	if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelI.minutosGanhos")) {
                        p.sendMessage("§cVocê ja possui esse upgrade.");
                        return;
                    	}
                        if (ShieldAPI.getPoints(p2.getFactionTag()) < config.getDouble("Upgrades.NivelI.preco")) {
                            p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelI.preco")) + " §cPontos para comprar isto.");
                            return;
                        }
                        p.sendMessage("§aYeah! Upgrade I comprado com sucesso.");
                        ShieldAPI.removePontos(p2.getFactionTag(), config.getDouble("Upgrades.NivelI.preco"));
                        ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelI.minutosGanhos"));
                        p.closeInventory();
                        p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                    }
                    if (e.getSlot() == 13) {
                        if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelII.minutosGanhos")) {
                            p.sendMessage("§cVocê ja possui esse upgrade.");
                            return;
                        }
                        if (ShieldAPI.getNivel(p2.getFactionTag()) < 1) {
                            p.sendMessage("§cPara dar outro upgrade você precisa ser Nivel I.");
                            return;
                        }
                        if (ShieldAPI.getPoints(p2.getFactionTag()) < config.getDouble("Upgrades.NivelII.preco")) {
                            p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelII.preco")) + " §cPontos para comprar isto.");
                            return;
                        }
                        p.sendMessage("§aYeah! Upgrade II comprado com sucesso.");
                        ShieldAPI.removePontos(p2.getFactionTag(), config.getDouble("Upgrades.NivelII.preco"));
                        ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelII.minutosGanhos"));
                        p.closeInventory();
                        p.openInventory(InventoryManagers.viewUpgradesMenu(p));      
                    }
                    else if (e.getSlot() == 14) {
                        if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelIII.minutosGanhos")) {
                            p.sendMessage("§cVocê ja possui esse upgrade.");
                            return;
                        }
                        if (ShieldAPI.getNivel(p2.getFactionTag()) < config.getInt("Upgrades.NivelII.minutosGanhos")) {
                            p.sendMessage("§cPara dar outro upgrade você precisa ser Nivel II.");
                            return;
                        }
                        if (ShieldAPI.getPoints(p2.getFactionTag()) < config.getDouble("Upgrades.NivelIII.preco")) {
                            p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelIII.preco")) + " §cPontos para comprar isto.");
                            return;
                        }
                        p.sendMessage("§aYeah! Upgrade III comprado com sucesso.");
                        ShieldAPI.removePontos(p2.getFactionTag(), config.getDouble("Upgrades.NivelIII.preco"));
                        ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelIII.minutosGanhos"));
                        p.closeInventory();
                        p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                 }
               }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase(config.getString("Menu.Verificar.name").replace("&", "§"))) {
                e.setCancelled(true);
            }
        }   
    
            static {
                DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
                FORMATTER = new DecimalFormat("#,##0.0", ClickEvent.DFS);
            }

            
            
@EventHandler
public void bc(final InventoryClickEvent e) {
	
    final Player p = (Player)e.getWhoClicked();
    final MPlayer p2 = MPlayer.get((Object)p);
    final ItemStack item = e.getCurrentItem();
    FileConfiguration config = Main.get().getConfig();
    
        if (e.getInventory().getName().equalsIgnoreCase(config.getString("Menu.principal.nome-menu").replace("&", "§").replace("{tag}", p2.getFactionTag()))) {
            e.setCancelled(true);
          if (config.getBoolean("Utils.Money")) {  
               	if (e.getSlot() == config.getInt("Menu.principal.melhorias.Slot")) {
                    p.openInventory(InventoryManagers.viewUpgradesMenu(p));
            	}
            	
                else if (e.getSlot() == config.getInt("Menu.principal.escudoComprar.Slot")) {
                    if (ShieldAPI.getEscudo(p2.getFactionTag())) {
                        p.sendMessage("§cSua facção ja possui um Escudo ativo.");
                        return;
                    }
                    
                    if (Main.get().getEconomy().getBalance((OfflinePlayer)p) < config.getInt("Escudo.ComprarEscudo")) {
                        p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getInt("Escudo.ComprarEscudo")) + " §cMoney para comprar isto.");
                        return;
                    }
                    if (config.getString("Utils.compra").equalsIgnoreCase("COMPRA")) {
                    p2.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer(), config.getString("Mensagens.EscudoComprado").replace("&", "§").replace("{tag}", p2.getFactionTag())));               	         
                    Main.get().getEconomy().withdrawPlayer((OfflinePlayer)p, (double)config.getInt("Escudo.ComprarEscudo"));
                    ShieldAPI.setEscudo(p2.getFactionTag(), 1);
                    p.closeInventory();
                    p.openInventory(InventoryManagers.shieldMenu(p));
                    }
                    if (config.getString("Utils.compra").equalsIgnoreCase("ANUNCIAR")) {
                   	 TitleAPI.sendTitle(p, 20, 30, 20, config.getString("Mensagens.EscudoAnunciar-Tittle").replace("&", "§").replace("{tag}", p2.getFactionTag()), config.getString("Mensagens.EscudoAnunciar-SubTittle").replace("&", "§").replace("{tag}", p2.getFactionTag()));               	         
                     Main.get().getEconomy().withdrawPlayer((OfflinePlayer)p, (double)config.getInt("Escudo.ComprarEscudo"));
                     ShieldAPI.setEscudo(p2.getFactionTag(), 1);
                     p.closeInventory();
                     p.openInventory(InventoryManagers.shieldMenu(p));
                    }
                    
                    final int time = config.getInt("Escudo.Duracao") + ShieldAPI.getNivel(p2.getFactionTag());
                    new BukkitRunnable() {
                        public void run() {
                            ShieldAPI.setEscudo(p2.getFactionTag(), 0);
                            p2.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer(), config.getString("Mensagens.EscudoTerminou").replace("&", "§").replace("{tag}", p2.getFactionTag())));
                        }
                    }.runTaskLater((Plugin)Main.get(), time * 20L);              
                }
          }
      }
        else if (e.getInventory().getTitle().equals(config.getString("Menu.Upgrades.name").replace("&", "§").replace("{tag}", p2.getFactionTag()))) {
            e.setCancelled(true);
            if (config.getBoolean("Utils.Money")) {  
            if (e.getClickedInventory() != null && item != null && item.getType() != Material.AIR) {
                if (e.getSlot() == 12) {
                	if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelI.minutosGanhos")) {
                    p.sendMessage("§cVocê ja possui esse upgrade.");
                    return;
                	}
                	 if (Main.get().getEconomy().getBalance((OfflinePlayer)p) < config.getInt("Upgrades.NivelI.preco")) {
                        p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelI.preco")) + " §cMoney para comprar isto.");
                        return;
                    }
                    p.sendMessage("§aYeah! Upgrade I comprado com sucesso.");
                    Main.get().getEconomy().withdrawPlayer((OfflinePlayer)p, (double)config.getInt("Upgrades.NivelI.preco"));
                    ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelI.minutosGanhos"));
                    p.closeInventory();
                    p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                }
                if (e.getSlot() == 13) {
                    if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelII.minutosGanhos")) {
                        p.sendMessage("§cVocê ja possui esse upgrade.");
                        return;
                    }
                    if (ShieldAPI.getNivel(p2.getFactionTag()) < 1) {
                        p.sendMessage("§cPara dar outro upgrade você precisa ser Nivel I.");
                        return;
                    }
                    if (Main.get().getEconomy().getBalance((OfflinePlayer)p) < config.getInt("Upgrades.NivelII.preco")) {
                        p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelII.preco")) + " §cMoney para comprar isto.");
                        return;
                    }
                    p.sendMessage("§aYeah! Upgrade II comprado com sucesso.");
                    Main.get().getEconomy().withdrawPlayer((OfflinePlayer)p, (double)config.getInt("Upgrades.NivelII.preco"));
                    ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelII.minutosGanhos"));
                    p.closeInventory();
                    p.openInventory(InventoryManagers.viewUpgradesMenu(p));      
                }
                else if (e.getSlot() == 14) {
                    if (ShieldAPI.getNivel(p2.getFactionTag()) >= config.getInt("Upgrades.NivelIII.minutosGanhos")) {
                        p.sendMessage("§cVocê ja possui esse upgrade.");
                        return;
                    }
                    if (ShieldAPI.getNivel(p2.getFactionTag()) < config.getInt("Upgrades.NivelII.minutosGanhos")) {
                        p.sendMessage("§cPara dar outro upgrade você precisa ser Nivel II.");
                        return;
                    }
                    if (Main.get().getEconomy().getBalance((OfflinePlayer)p) < config.getInt("Upgrades.NivelIII.preco")) {
                        p.sendMessage("§cSua facção precisa possuir §f" + ClickEvent.FORMATTER.format(config.getDouble("Upgrades.NivelIII.preco")) + " §cMoney para comprar isto.");
                        return;
                    }
                    p.sendMessage("§aYeah! Upgrade III comprado com sucesso.");
                    Main.get().getEconomy().withdrawPlayer((OfflinePlayer)p, (double)config.getInt("Upgrades.NivelIII.preco"));
                    ShieldAPI.setNivel(p2.getFactionTag(), config.getInt("Upgrades.NivelIII.minutosGanhos"));
                    p.closeInventory();
                    p.openInventory(InventoryManagers.viewUpgradesMenu(p));
             } 
            }
            }
        }
        else if (e.getInventory().getName().equalsIgnoreCase(config.getString("Menu.Verificar.name").replace("&", "§"))) {
            e.setCancelled(true);
        }
    }   

        static {
            DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
            FORMATTER = new DecimalFormat("#,##0.0", ClickEvent.DFS);
        }

  }    
