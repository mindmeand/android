package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;
import java.util.List;

public class UserMyInfoList implements Serializable {

    private String result;
    private User user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
