package com.example.myapplication;

import java.util.Date;

public class User {
    private String name, password, email;
    private Date birthday;
    private Float weight;

    public User() {}

    public User(String name, String password, String email, Date birthday, Float weight) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
