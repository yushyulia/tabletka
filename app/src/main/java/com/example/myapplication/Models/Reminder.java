package com.example.myapplication.Models;

import java.util.ArrayList;

public class Reminder {
    private String moment,days, time;

    public  Reminder() {}

    public Reminder(String time, String moment, String days){
        this.time = time;
        this.moment = moment;
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
