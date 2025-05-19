package com.taxapp;

import java.time.LocalDate;
import java.util.*;

import com.taxapp.dao.TaxRecordDAO;
import com.taxapp.model.TaxRecord;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        TaxRecordDAO dao = new TaxRecordDAO();
        dao.insert(new TaxRecord("Copenhagen", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 1), "DAILY", 0.1));
        dao.insert(new TaxRecord("Copenhagen", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), "YEARLY", 0.2));
        dao.insert(new TaxRecord("Copenhagen", LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 31), "MONTHLY", 0.4));
        dao.insert(new TaxRecord("Aarhus", LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30), "MONTHLY", 0.3));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter municipality: ");
        String municipality = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        scanner.close();
        List<TaxRecord> records = dao.find(municipality, date);
        TaxRecord best = null;
        for (TaxRecord r : records) {
            if (best == null || getPriority(r.taxType) < getPriority(best.taxType)) {
                best = r;
            }
        }
        if (best != null) {
            System.out.println("Tax Rate: " + best.rate);
        } else {
            System.out.println("No tax rate found.");
        }
    }

    public static int getPriority(String type) {
        switch (type.toUpperCase()) {
            case "DAILY":
                return 1;
            case "WEEKLY":
                return 2;
            case "MONTHLY":
                return 3;
            case "YEARLY":
                return 4;
            default:
                return 5;
        }
    }
}
