package com.taxapp.model;

import java.time.LocalDate;

public class TaxRecord {
    public String municipality;
    public LocalDate start;
    public LocalDate end;
    public String taxType;
    public double rate;

    public TaxRecord(String municipality, LocalDate start, LocalDate end, String taxType, double rate) {
        this.municipality = municipality;
        this.start = start;
        this.end = end;
        this.taxType = taxType;
        this.rate = rate;
    }
}