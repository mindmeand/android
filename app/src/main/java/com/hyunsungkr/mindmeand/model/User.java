package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String password;
    private String birthDate;
    private String email;



    public User(){}


    public User(String name, String birthday, String email, String password) {
        this.name = name;
        this.birthDate = birthday;
        this.email = email;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String birthday, String email) {
        this.name = name;
        this.birthDate = birthday;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
