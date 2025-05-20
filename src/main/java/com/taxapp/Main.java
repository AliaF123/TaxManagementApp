package com.taxapp;

import com.taxapp.dao.TaxRecordDAO;
import com.taxapp.model.TaxRecord;
import com.taxapp.service.TaxService;
import com.taxapp.DatabaseManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();

        TaxRecordDAO dao = new TaxRecordDAO();
        TaxService service = new TaxService(dao);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tax Management Application");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Query tax");
            System.out.println("2. Add tax record");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println("Enter municipality: ");
                String municipality = scanner.nextLine();

                System.out.println("Enter date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                TaxRecord record = service.getBestTaxRecord(municipality, date);
                if (record != null) {
                    System.out.println("Tax rate for " + municipality + " on " + date + " is: " + record.getRate());
                } else {
                    System.out.println("No tax record found.");
                }
            } else if (choice == 2) {
                System.out.println("Enter municipality: ");
                String municipality = scanner.nextLine();

                System.out.println("Enter start date (YYYY-MM-DD): ");
                LocalDate startDate = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter end date (YYYY-MM-DD): ");
                LocalDate endDate = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter tax type (DAILY, WEEKLY, MONTHLY, YEARLY): ");
                String taxType = scanner.nextLine();

                System.out.println("Enter base annual tax rate (e.g. 0.2): ");
                double baseRate = Double.parseDouble(scanner.nextLine());

                service.addTaxRecord(municipality, startDate, endDate, taxType, baseRate);
                System.out.println("Tax record added.");
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}