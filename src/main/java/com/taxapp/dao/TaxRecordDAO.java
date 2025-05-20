package com.taxapp.dao;

import com.taxapp.model.*;
import java.time.LocalDate;
import java.util.*;

public class TaxRecordDAO {
    private final List<TaxRecord> records = new ArrayList<>();

    public TaxRecordDAO() {
        seedData();
    }

    private void seedData() {
        records.add(
                new TaxRecord("Copenhagen", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31), TaxType.YEARLY, 0.2));
        records.add(
                new TaxRecord("Copenhagen", LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 31), TaxType.MONTHLY, 0.4));
        records.add(
                new TaxRecord("Copenhagen", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 1), TaxType.DAILY, 0.1));
        records.add(new TaxRecord("Copenhagen", LocalDate.of(2025, 12, 25), LocalDate.of(2025, 12, 25), TaxType.DAILY,
                0.1));
    }

    public List<TaxRecord> findByMunicipalityAndDate(String municipality, LocalDate date) {
        List<TaxRecord> result = new ArrayList<>();
        for (TaxRecord r : records) {
            if (r.getMunicipality().equalsIgnoreCase(municipality) && r.isDateInRange(date)) {
                result.add(r);
            }
        }
        return result;
    }

    public void addRecord(TaxRecord record) {
        records.add(record);
    }
}