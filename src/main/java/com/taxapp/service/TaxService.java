package com.taxapp.service;

import com.taxapp.dao.TaxRecordDAO;
import com.taxapp.model.*;

import java.time.LocalDate;
import java.util.List;

public class TaxService {
    private final TaxRecordDAO dao;

    public TaxService(TaxRecordDAO dao) {
        this.dao = dao;
    }

    // Change parameter type to TaxType enum
    private int getPriority(TaxType taxType) {
        switch (taxType) {
            case DAILY:
                return 1;
            case WEEKLY:
                return 2;
            case MONTHLY:
                return 3;
            case YEARLY:
                return 4;
            default:
                return 5;
        }
    }

    public TaxRecord getBestTaxRecord(String municipality, LocalDate date) {
        List<TaxRecord> items = dao.findByMunicipalityAndDate(municipality, date);
        TaxRecord best = null;

        for (TaxRecord record : items) {
            if (best == null || getPriority(record.getTaxType()) < getPriority(best.getTaxType())) {
                best = record;
            }
        }
        return best;
    }

    public void addTaxRecord(String municipality, LocalDate startDate, LocalDate endDate, String type, double rate) {
        TaxType taxType = TaxType.valueOf(type.toUpperCase());
        TaxRecord record = new TaxRecord(municipality, startDate, endDate, taxType, rate);
        dao.addRecord(record);
    }
}
