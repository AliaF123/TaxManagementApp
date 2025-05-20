package com.taxapp.model;

import java.time.LocalDate;

public class TaxRecord {
    private String municipality;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaxType taxType;
    private double rate;

    public TaxRecord(String municipality, LocalDate startDate, LocalDate endDate, TaxType taxType, double rate) {
        this.municipality = municipality;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taxType = taxType;
        this.rate = rate;
    }

    public boolean isDateInRange(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public String getMunicipality() {
        return municipality;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public double getRate() {
        return rate;
    }
}
