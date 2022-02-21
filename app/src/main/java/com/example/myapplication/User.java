package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable {

    private String user_name, email, password, birthday;
    private Long weight;

    public User(String user_name, String email, String password, String birthday, Long weight){
        this.user_name=user_name;
        this.email=email;
        this.password=password;
        this.birthday=birthday;
        this.weight=weight;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday(){
        return  birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getWeight(){
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
