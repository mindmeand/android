package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;

public class Consultation implements Serializable {
    private int type;
    private String question;

    public Consultation() {
    }

    public Consultation(int type, String question) {
        this.type = type;
        this.question = question;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
