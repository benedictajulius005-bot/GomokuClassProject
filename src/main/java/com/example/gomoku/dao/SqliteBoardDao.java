package com.example.gomoku.dao;

import com.example.gomoku.util.DbManager;
import java.sql.*;

/**
 * SQLite-based implementation of the {@link BoardDao} interface.
 * <p>
 * This class is responsible for persisting and retrieving Gomoku boards
 * using an SQLite database. It automatically initializes the required table
 * if it does not already exist.
 */
public class SqliteBoardDao implements BoardDao {

    /**
     * Constructs a new {@code SqliteBoardDao} and ensures that the "boards" table
     * exists in the SQLite database. If the table does not exist, it is created.
     * <p>
     * Table structure:
     * <ul>
     *     <li><b>name</b> – TEXT PRIMARY KEY, the identifier of the board</li>
     *     <li><b>data</b> – TEXT, the serialized board representation</li>
     * </ul>
     *
     * @throws RuntimeException if a database error occurs during initialization
     */
    public SqliteBoardDao() {
        try (Connection c = DbManager.getInstance().getConnection()) {
            c.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS boards (" +
                            "name TEXT PRIMARY KEY, " +
                            "data TEXT)"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves a serialized board into the SQLite database.
     * <p>
     * If the given name already exists, the record is replaced.
     *
     * @param name the name used as the key for saving the board
     * @param serializedBoard the board converted into a String representation
     *
     * @throws RuntimeException if a database write error occurs
     */
    @Override
    public void save(String name, String serializedBoard) {
        String sql = "REPLACE INTO boards(name, data) VALUES(?, ?)";

        try (Connection c = DbManager.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, serializedBoard);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a serialized board from the database.
     *
     * @param name the key/name previously used to store the board
     * @return the serialized board as a String, or {@code null} if no board exists with that name
     *
     * @throws RuntimeException if a database read error occurs
     */
    @Override
    public String load(String name) {
        String sql = "SELECT data FROM boards WHERE name=?";

        try (Connection c = DbManager.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("data");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
