package model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    File file = new File("fortniteconn.db");
    String path = file.getAbsolutePath();

    public final String CONNECTION_STRING = "jdbc:sqlite:" + path;
    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    String query = "SELECT * FROM GAMES";

    public List<Game> queryGames() {
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(query)) {
            List<Game> games = new ArrayList<>();
            while (results.next()) {
                Game game = new Game();
                game.setId(results.getInt(2));
                int id = results.getInt(2);
                game.setName(results.getString(1));
                String name = results.getString(1);
                System.out.println(name + ":" + " ID: " + id);
                game.setConnections(results.getInt(3));
                games.add(game);
            }

            return games;
        } catch (SQLException e) {
            System.out.println("Query failed.");
        }
        return null;
    }


    public void queryConnection(int id) {
        String name = queryById(id);
        System.out.println(name + " is connected to: ");
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT Connections FROM Games WHERE id = " + id)) {
            String gameName = results.getString(1);
            System.out.println(gameName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int queryByName(String Name) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT id FROM Games WHERE Name = " + "'" + Name + "'")) {
            int Conn = results.getInt(1);
            System.out.println(Conn);
            return Conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String queryById(int id) {
        if (id != 0) {
            try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery("SELECT Name FROM Games WHERE id = " + id)) {
                String name = results.getString(1);
                return name;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fortnite");
        }
        return "Could not connect";

    }
    
}












