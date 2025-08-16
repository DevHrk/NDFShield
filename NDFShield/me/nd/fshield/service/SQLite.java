package me.nd.fshield.service;

import java.io.*;
import org.bukkit.*;
import java.sql.*;

public class SQLite {
    private static Connection connection;
    
    public static void openConnection() {
        File file = new File("plugins/NDFshield/database.db");
        String url = ("jdbc:sqlite:" + file);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            Bukkit.getConsoleSender().sendMessage("§b[NDFshield] Conexão com §fSQLite §baberta com sucesso");
            createTables();
        }
        catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§b[NDFshield] §cHouve um erro ao tentar fazer conexão com §6SQLite");
        }
    }
    
   
    
    public static void closeConnection() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
            Bukkit.getConsoleSender().sendMessage("§b[NDFshield] §cConexão com SQLite fechada com sucesso");
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§b[NDFshield] §cOcorreu um erro ao tentar fechar a conexão com o SQLite, erro:");
            e.printStackTrace();
        }
    }

    public static boolean executeQuery(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createFacInDB(final String tag) {
        executeQuery("INSERT INTO shield VALUES ('" + tag + "', 0, 1, 0, 0)");
    }

    public static void createTables() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `shield` (tag VARCHAR(32), pontos DOUBLE, NIVEL INTEGER, escudoAtivado BOOLEAN, expire_time INTEGER)");
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
