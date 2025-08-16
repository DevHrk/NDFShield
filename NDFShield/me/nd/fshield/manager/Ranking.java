package me.nd.fshield.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.util.Banner;

import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.utils.FormatterShield;

public class Ranking {
	public static void open(Player p) {
		    AtomicInteger pos = new AtomicInteger(1);
	        final MPlayer p2 = MPlayer.get((Object)p);
	        final List<ItemStack> itens = new ArrayList<ItemStack>();
		 
            ItemStack item = new ItemStack(Banner.getWhiteBanner(p2.getFactionTag()));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§f" + pos + "º §7[" + p2.getFactionTag() + "] " + p2.getFactionName());
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§fTotal em Pontos: §7" + FormatterShield.formatNumber(ShieldAPI.getPoints(p2.getFactionTag())));
            meta.setLore(lore);
            item.setItemMeta(meta);
            itens.add(item);
            pos.getAndIncrement();
        ScrollerRanking scroller = new ScrollerRanking.ScrollerBuilder().withSize(54).withName("Top Facções - Pontos").withItems(itens).withItemsSlots(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34).withArrowsSlots(45, 53).build();
        scroller.open(p);
    }
}
