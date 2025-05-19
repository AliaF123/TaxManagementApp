package com.taxapp.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.taxapp.DatabaseManager;
import com.taxapp.model.TaxRecord;

public class TaxRecordDAO {

    // Insert a TaxRecord into the database
    public void insert(TaxRecord r) {
        String sql = "INSERT INTO tax_records (municipality, rate, start_date, end_date, tax_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.municipality);
            stmt.setDouble(2, r.rate);
            stmt.setString(3, r.start.toString());
            stmt.setString(4, r.end.toString());
            stmt.setString(5, r.taxType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed", e);
        }
    }

    // Find all tax records for a municipality that apply on a specific date
    public List<TaxRecord> find(String municipality, LocalDate date) {
        String sql = "SELECT * FROM tax_records WHERE municipality = ? AND start_date <= ? AND end_date >= ?";
        List<TaxRecord> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, municipality);
            stmt.setString(2, date.toString());
            stmt.setString(3, date.toString());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TaxRecord record = new TaxRecord(
                        rs.getString("municipality"),
                        LocalDate.parse(rs.getString("start_date")),
                        LocalDate.parse(rs.getString("end_date")),
                        rs.getString("tax_type"),
                        rs.getDouble("rate"));
                list.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Select failed", e);
        }
        return list;
    }
}
