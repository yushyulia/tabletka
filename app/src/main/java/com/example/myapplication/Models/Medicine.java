package com.example.myapplication.Models;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Medicine {
    private String name, applying, units;
    private String dosage;
    private Date firstDay;
    private Date lastDay;

    public  Medicine() {}

    public Medicine(String name, String dosage, String units, String applying, Date firstDay, Date lastDay) {
        this.name = name;
        this.dosage = dosage;
        this.units = units;
        this.applying = applying;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public String getApplying() {
        return applying;
    }

    public void setApplying(String applying) {
        this.applying = applying;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("applying", applying);
        result.put("units", units);
        result.put("name", name);
        result.put("dosage", dosage);
        result.put("firstDay", firstDay);
        result.put("lastDay", lastDay);

        return result;
    }
}
