package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;
import java.util.List;

public class UserMyInfoList implements Serializable {

    private String result;
    private List<User> user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
