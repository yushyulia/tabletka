package com.example.myapplication;

import java.util.Date;

public class Medicine {
    private Integer id;
    private String name;
    private Float dosage;
    private String[] units;
    private String[] applying;
    private Date firstDay;
    private Date lastDay;

    public Medicine(Integer id, String name, Float dosage, String[] units, String[] applying, Date firstDay, Date lastDay) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.units = units;
        this.applying = applying;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDosage() {
        return dosage;
    }

    public void setDosage(Float dosage) {
        this.dosage = dosage;
    }

    public String[] getUnits() {
        return units;
    }

    public void setUnits(String[] units) {
        this.units = units;
    }

    public String[] getApplying() {
        return applying;
    }

    public void setApplying(String[] applying) {
        this.applying = applying;
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
}
