package me.nd.fshield.api;

import org.bukkit.*;

import me.nd.fshield.service.SQLite;

import java.sql.*;

public class ShieldAPI extends SQLite {

	public static void setupFactions() {
		    try {
		      PreparedStatement stm = SQLite.getConnection().prepareStatement("SELECT * FROM shield");
		      ResultSet rs = stm.executeQuery();
		      while (rs.next()) {
		        String tag = rs.getString("tag");
		        setEscudo(tag, 0);
		      } 
		      Bukkit.getConsoleSender().sendMessage("§b[NDFshield] Todas as facções foram carregadas.");
		    } catch (SQLException ex) {
		      ex.printStackTrace();
		      Bukkit.getConsoleSender().sendMessage("§b[NDFshield] §cErro ao carregar as facções.");
		    } 
		  }

    public static void deleteFac(final String tag) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("DELETE FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            stm.executeUpdate();
        }
        catch (SQLException ex) {}
    }
    
    public static Double getPoints(final String tag) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            final ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("pontos");
            }
            return 0.0;
        }
        catch (SQLException e) {
            return 0.0;
        }
    }
    
    public static Integer getNivel(final String tag) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            final ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("nivel");
            }
            return 0;
        }
        catch (SQLException e) {
            return 0;
        }
    }
    
    public static Boolean getEscudo(final String tag) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("SELECT * FROM `shield` WHERE `tag` = ?");
            stm.setString(1, tag);
            final ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("escudoAtivado");
            }
            return false;
        }
        catch (SQLException e) {
            return false;
        }
    }
    
    public static void setNivel(final String tag, final int quantia) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("UPDATE `shield` SET `nivel` = ? WHERE `tag` = ?");
            stm.setInt(1, quantia);
            stm.setString(2, tag);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void setEscudo(final String tag, final int quantia) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("UPDATE `shield` SET `escudoAtivado` = ? WHERE `tag` = ?");
            stm.setInt(1, quantia);
            stm.setString(2, tag);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addPontos(final String tag, final double amount) {
        PreparedStatement stm = null;
        final double pontosTotal = getPoints(tag) + amount;
        try {
            stm = SQLite.getConnection().prepareStatement("UPDATE `shield` SET pontos= ? WHERE `tag` = ?");
            stm.setDouble(1, pontosTotal);
            stm.setString(2, tag);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void removePontos(final String tag, final double amount) {
        PreparedStatement stm = null;
        final double pontosTotal = getPoints(tag) - amount;
        try {
            stm = SQLite.getConnection().prepareStatement("UPDATE `shield` SET pontos= ? WHERE `tag` = ?");
            stm.setDouble(1, pontosTotal);
            stm.setString(2, tag);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setExpireTime(final String tag, final long expireTime) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("UPDATE `shield` SET `expire_time` =? WHERE `tag` =?");
            stm.setLong(1, expireTime);
            stm.setString(2, tag);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long getExpireTime(final String tag) {
        PreparedStatement stm = null;
        try {
            stm = SQLite.getConnection().prepareStatement("SELECT `expire_time` FROM `shield` WHERE `tag` =?");
            stm.setString(1, tag);
            final ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getLong("expire_time");
            }
            return 0;
        }
        catch (SQLException e) {
            return 0;
        }
    }
}
