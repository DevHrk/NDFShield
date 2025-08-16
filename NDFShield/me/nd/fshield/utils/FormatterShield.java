package me.nd.fshield.utils;

import me.nd.fshield.Main;

import org.bukkit.configuration.file.*;
import java.util.*;
import java.text.*;
import java.util.regex.*;

public class FormatterShield {
	
	static FileConfiguration config1 = Main.get().getConfig();
    @SuppressWarnings("unused")
	private static Main main;
    private static final Pattern PATTERN;
    @SuppressWarnings("unused")
	private static FileConfiguration config;
    private static List<String> suffixes;
    
    public static void changeSuffixes(final List<String> suffixes) {
    	FormatterShield.suffixes = suffixes;
    }
    
    public static String formatNumber(double value) {
        int index;
        double tmp;
        for (index = 0; (tmp = value / 1000.0) >= 1.0; value = tmp, ++index) {}
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return String.valueOf(decimalFormat.format(value)) + FormatterShield.suffixes.get(index);
    }
    
    public static double parseString(final String value) throws Exception {
        try {
            return Double.parseDouble(value);
        }
        catch (Exception exception) {
            final Matcher matcher = FormatterShield.PATTERN.matcher(value);
            if (!matcher.find()) {
                throw new Exception("Invalid format");
            }
            final double amount = Double.parseDouble(matcher.group(1));
            final String suffix = matcher.group(2);
            final int index = FormatterShield.suffixes.indexOf(suffix.toUpperCase());
            return amount * Math.pow(1000.0, index);
        }
    }
    
    static {
    	FormatterShield.main = (Main)Main.getPlugin((Class)Main.class);
        PATTERN = Pattern.compile("^(\\d+\\.?\\d*)(\\D+)");
        FormatterShield.config = Main.get().getConfig();
        FormatterShield.suffixes = (List<String>)FormatterShield.config1.getStringList("Formatação");
    }
}