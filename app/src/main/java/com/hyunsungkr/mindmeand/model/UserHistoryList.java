package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;
import java.util.List;

public class UserHistoryList implements Serializable {

    private List<UserHistory> result;
    private int count;

    public UserHistoryList(List<UserHistory> result, int count) {
        this.result = result;
        this.count = count;
    }

    public List<UserHistory> getResult() {
        return result;
    }

    public void setResult(List<UserHistory> result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
