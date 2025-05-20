package com.taxapp;

import java.sql.*;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:sqlite:taxrecord.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    public static void initializeDatabase() {
        String dropTable = "DROP TABLE IF EXISTS tax_records;";
        String createTable = "CREATE TABLE IF NOT EXISTS tax_records (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    municipality TEXT NOT NULL,\n" +
                "    rate REAL NOT NULL,\n" +
                "    start_date TEXT NOT NULL,\n" +
                "    end_date TEXT NOT NULL,\n" +
                "    tax_type TEXT NOT NULL\n" +
                ");";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(dropTable);
            stmt.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}