package com.example.myapplication.Models;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medicine {
    private String id, name, applying, units, firstDay, lastDay;
    private String dosage;
    private Reminder reminder;

    public  Medicine() {}

    public Medicine(String id,String name, String dosage, String units, String applying, String firstDay, String lastDay, Reminder reminder) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.units = units;
        this.applying = applying;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.reminder = reminder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
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
        result.put("reminder",reminder);
        result.put("id",id);
        return result;
    }
}
