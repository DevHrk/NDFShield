package me.nd.fshield.list;

import org.bukkit.event.entity.*;
import com.massivecraft.factions.entity.*;

import me.nd.fshield.Main;
import me.nd.fshield.api.ShieldAPI;
import me.nd.fshield.utils.ActionBarAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.configuration.file.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.*;

public class EntityEvent implements Listener
{
	private static Map<EntityType, Map<String, Double>> mobPoints = new HashMap<>();

	public static void loadMobPoints() {
	    FileConfiguration config = Main.get().getConfig();
	    List<String> mobs = config.getStringList("Mobs");
	    for (String mob : mobs) {
	        String[] parts = mob.split(":");
	        EntityType entityType = EntityType.valueOf(parts[0]);
	        double points = Double.parseDouble(parts[1]);
	        double chance = Double.parseDouble(parts[2]);
	        Map<String, Double> mobConfig = new HashMap<>();
	        mobConfig.put("points", points);
	        mobConfig.put("chance", chance);
	        mobPoints.put(entityType, mobConfig);
	    }
	}

	@EventHandler
	void a(final EntityDeathEvent e) {
	    final MPlayer killer = MPlayer.get((Object)e.getEntity().getKiller());
	    if (e.getEntity().getKiller()!= null) {
	        if (killer.getFactionTag() == null && killer.getFaction() == null && killer.getFactionName() == null) {
	            return;
	        }
	        EntityType entityType = e.getEntity().getType();
	        if (mobPoints.containsKey(entityType)) {
	            Map<String, Double> mobConfig = mobPoints.get(entityType);
	            double points = mobConfig.get("points");
	            double chance = mobConfig.get("chance");
	            if (new Random().nextInt(101) <= chance) {
	                ShieldAPI.addPontos(killer.getFactionTag(), points);
	                ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), Main.get().getConfig().getString("Mensagens.ActionBar").replace("&", "§").replace("{quantia}", "" + points));
	            }
	        }
	    }
	}
}
