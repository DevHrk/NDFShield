package me.nd.fshield.utils;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;
import java.util.*;

public class ActionBarAPI
{
    private static final Map<Player, BukkitTask> PENDING_MESSAGES;
    
    public static void sendActionBarMessage(final Player bukkitPlayer, final String message) {
        sendRawActionBarMessage(bukkitPlayer, "{\"text\": \"" + message + "\"}");
    }
    
	public static void sendRawActionBarMessage(final Player bukkitPlayer, final String rawMessage) {
        final CraftPlayer player = (CraftPlayer)bukkitPlayer;
        final IChatBaseComponent chatBaseComponent = IChatBaseComponent.ChatSerializer.a(rawMessage);
        final PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chatBaseComponent, (byte)2);
        player.getHandle().playerConnection.sendPacket((Packet)packetPlayOutChat);
    }
    
    public static void sendActionBarMessage(final Player bukkitPlayer, final String message, final int duration, final Plugin plugin) {
        cancelPendingMessages(bukkitPlayer);
        final BukkitTask messageTask = new BukkitRunnable() {
            private int count = 0;
            
            public void run() {
                if (this.count >= duration - 3) {
                    this.cancel();
                }
                ActionBarAPI.sendActionBarMessage(bukkitPlayer, message);
                ++this.count;
            }
        }.runTaskTimer(plugin, 0L, 20L);
        ActionBarAPI.PENDING_MESSAGES.put(bukkitPlayer, messageTask);
    }
    
    private static void cancelPendingMessages(final Player bukkitPlayer) {
        if (ActionBarAPI.PENDING_MESSAGES.containsKey(bukkitPlayer)) {
            ActionBarAPI.PENDING_MESSAGES.get(bukkitPlayer).cancel();
        }
    }
    
    static {
        PENDING_MESSAGES = new HashMap<Player, BukkitTask>();
    }
}
