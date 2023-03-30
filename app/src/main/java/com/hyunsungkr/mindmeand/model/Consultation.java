package com.hyunsungkr.mindmeand.model;

import java.io.Serializable;

public class Consultation implements Serializable {
    private int type;
    private String question;
    private String answer;

    public Consultation() {
    }

    public Consultation(int type, String question) {
        this.type = type;
        this.question = question;
    }

    public Consultation(int type, String question, String answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
