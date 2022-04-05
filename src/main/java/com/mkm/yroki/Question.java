package com.mkm.yroki;

import java.util.List;

/**
 * Created by papa on 02.04.2022
 */

public class Question {

    String text;
    List<String> answers;
    int num = 0;
    int right = 0;
    String answered = "";

    public Question(String text, List<String> answers, int right) {
        this.text = text;
        this.answers = answers;
        this.right = right;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public boolean isRightAnswer(String answer) {
        boolean right = getAnswers().get(this.right).equals(answer);
        return right;
    }
}
