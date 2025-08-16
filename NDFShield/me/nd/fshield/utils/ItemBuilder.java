package me.nd.fshield.utils;

import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.function.*;

public class ItemBuilder
{
    private ItemStack item;
    
    public ItemBuilder(final Material m) {
        this(m, 1);
    }
    
    public ItemBuilder(final ItemStack is) {
        this.item = is;
    }
    
    public ItemBuilder(final Material m, final int quantia) {
        this.item = new ItemStack(m, quantia);
    }
    
    public ItemBuilder(final Material m, final int quantia, final byte durabilidade) {
        this.item = new ItemStack(m, quantia, (short)durabilidade);
    }
    
    public ItemBuilder(final Material m, final int quantia, final int durabilidade) {
        this.item = new ItemStack(m, quantia, (short)durabilidade);
    }
    
    public ItemBuilder clone() {
        return new ItemBuilder(this.item);
    }
    
    public ItemBuilder setDurability(final short durabilidade) {
        this.item.setDurability(durabilidade);
        return this;
    }
    
    public ItemBuilder setAmount(final int amount) {
        this.item.setAmount(amount);
        final ItemMeta im = this.item.getItemMeta();
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
        this.item.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder setDurability(final String durabilidade) {
        this.item.setDurability((short)Short.valueOf(durabilidade));
        return this;
    }
    
    public ItemBuilder setName(final String name) {
        final ItemMeta im = this.item.getItemMeta();
        im.setDisplayName(name);
        this.item.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder addUnsafeEnchantment(final Enchantment ench, final int level) {
        this.item.addUnsafeEnchantment(ench, level);
        return this;
    }
    
    public ItemBuilder removeEnchantment(final Enchantment ench) {
        this.item.removeEnchantment(ench);
        return this;
    }
    
    public ItemBuilder setSkullOwner(final String dono) {
        try {
            final SkullMeta im = (SkullMeta)this.item.getItemMeta();
            im.setOwner(dono);
            this.item.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemBuilder addEnchant(final Enchantment ench, final int level) {
        final ItemMeta im = this.item.getItemMeta();
        im.addEnchant(ench, level, true);
        this.item.setItemMeta(im);
        return this;
    }
    

	public ItemBuilder addEnchantments(final Map<Enchantment, Integer> enchantments) {
        this.item.addEnchantments((Map)enchantments);
        return this;
    }
    
    public ItemBuilder setInfinityDurability() {
        this.item.setDurability((short)63);
        return this;
    }
    
    public ItemBuilder addItemFlag(final ItemFlag flag) {
        final ItemMeta im = this.item.getItemMeta();
        im.addItemFlags(new ItemFlag[] { flag });
        this.item.setItemMeta(im);
        return this;
    }
    

	public ItemBuilder setLore(final String... lore) {
        final ItemMeta im = this.item.getItemMeta();
        im.setLore((List)Arrays.asList(lore));
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(final List<String> lore) {
        final ItemMeta im = this.item.getItemMeta();
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(final String linha) {
        final ItemMeta im = this.item.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (!lore.contains(linha)) {
            return this;
        }
        lore.remove(linha);
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(final int index) {
        final ItemMeta im = this.item.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (index < 0 || index > lore.size()) {
            return this;
        }
        lore.remove(index);
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(final String linha) {
        final ItemMeta im = this.item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        lore.add(linha);
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLores(final List<String> linha) {
        final ItemMeta im = this.item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        for (final String s : linha) {
            lore.add(s);
        }
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(final String linha, final int pos) {
        final ItemMeta im = this.item.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        lore.set(pos, linha);
        im.setLore((List)lore);
        this.item.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder owner(final String owner) {
        try {
            final SkullMeta im = (SkullMeta)this.item.getItemMeta();
            im.setOwner(owner);
            this.item.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }

    public ItemBuilder lore(final String... lore) {
        final ItemMeta im = this.item.getItemMeta();
        final List<String> out = (im.getLore() == null) ? new ArrayList<String>() : im.getLore();
        final String[] arrayOfString = lore;
        final int i = lore.length;
        for (byte b = 0; b < i; ++b) {
            final String string = arrayOfString[b];
            out.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        im.setLore((List)out);
        this.item.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore2(final String... lore) {
        final ItemMeta im = this.item.getItemMeta();
        final List<String> out = (im.getLore() == null) ? new ArrayList<String>() : im.getLore();
        final String[] arrayOfString = lore;
        final int i = lore.length;
        for (byte b = 0; b < i; ++b) {
            final String string = arrayOfString[b];
            out.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        im.setLore((List)out);
        this.item.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder removeAttributes() {
        final ItemMeta meta = this.item.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        this.item.setItemMeta(meta);
        return this;
    }
    
    public ItemBuilder color(final Color color) {
        if (!this.item.getType().name().contains("LEATHER_")) {
            return this;
        }
        final LeatherArmorMeta meta = (LeatherArmorMeta)this.item.getItemMeta();
        meta.setColor(color);
        this.item.setItemMeta((ItemMeta)meta);
        return this;
    }
    
	public ItemBuilder setDyeColor(final DyeColor cor) {
        this.item.setDurability((short)cor.getData());
        return this;
    }
    
    @Deprecated
    public ItemBuilder setWoolColor(final DyeColor cor) {
        if (!this.item.getType().equals((Object)Material.WOOL)) {
            return this;
        }
        this.item.setDurability((short)cor.getData());
        return this;
    }
    
    public ItemBuilder setLeatherArmorColor(final Color cor) {
        try {
            final LeatherArmorMeta im = (LeatherArmorMeta)this.item.getItemMeta();
            im.setColor(cor);
            this.item.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemBuilder builder(final Consumer<ItemStack> consumer) {
        consumer.accept(this.item);
        return this;
    }
    
    public ItemBuilder builderMeta(final Consumer<ItemMeta> consumer) {
        final ItemMeta meta = this.item.getItemMeta();
        consumer.accept(meta);
        this.item.setItemMeta(meta);
        return this;
    }
    
    public ItemBuilder addGlow(final boolean glow) {
        if (!glow) {
            return this;
        }
        this.builder(it -> it.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1));
        this.builderMeta(meta -> meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS }));
        return this;
    }
    
    public ItemStack toItemStack() {
        return this.item;
    }
    
    public ItemStack build() {
        return this.item;
    }
}
