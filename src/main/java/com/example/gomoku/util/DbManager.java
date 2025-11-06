package com.example.gomoku.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final DbManager INSTANCE = new DbManager();
    private final String url = "jdbc:sqlite:gomoku.db";

    private DbManager() {}

    public static DbManager getInstance() { return INSTANCE; }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
