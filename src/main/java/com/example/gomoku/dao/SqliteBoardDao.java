package com.example.gomoku.dao;

import com.example.gomoku.util.DbManager;
import java.sql.*;

public class SqliteBoardDao implements BoardDao {
    public SqliteBoardDao() {
        try (Connection c = DbManager.getInstance().getConnection()) {
            c.createStatement().execute("CREATE TABLE IF NOT EXISTS boards (name TEXT PRIMARY KEY, data TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public String load(String name) {
        try (Connection c = DbManager.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT data FROM boards WHERE name=?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("data");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

