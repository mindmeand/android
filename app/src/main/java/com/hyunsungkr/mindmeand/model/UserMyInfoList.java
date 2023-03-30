package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;
import java.util.List;

public class UserMyInfoList implements Serializable {

    private String result;
    private User user;
    private int count;

    public UserMyInfoList(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



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
