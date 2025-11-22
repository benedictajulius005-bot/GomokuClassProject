package com.example.gomoku.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton class that manages SQLite database operations for Gomoku boards.
 * <p>
 * It provides methods to connect to the database, check if a board exists,
 * create the table if needed, and save boards.
 */
public class DbManager {
    /** Singleton instance of DbManager */
    private static final DbManager INSTANCE = new DbManager();

    /** JDBC URL for the SQLite database */
    private final String url = "jdbc:sqlite:gomoku.db";

    /** Private constructor to prevent external instantiation */
    private DbManager() {}

    /**
     * Returns the single instance of DbManager.
     *
     * @return the DbManager instance
     */
    public static DbManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a new connection to the SQLite database.
     *
     * @return a {@link Connection} object
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * Checks if a board with the given name already exists in the database.
     *
     * @param name the name of the board to check
     * @return true if the board exists, false otherwise
     */
    public boolean boardExists(String name) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT COUNT(*) FROM boards WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates the "boards" table if it does not already exist.
     * The table stores board ID, name, and serialized board data.
     */
    public void createTableIfNotExists() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS boards (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT UNIQUE, " +
                    "data TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a board to the database with the given name and serialized data.
     *
     * @param name the name of the board
     * @param data the serialized board data
     */
    public void saveBoard(String name, String data) {
        String sql = "INSERT INTO boards (name, data) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, data);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
