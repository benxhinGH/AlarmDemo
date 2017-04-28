package com.lius.alarmdemo.model;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class Question {
    private String subject;
    private int answer;

    public Question(String subject,int answer){
        this.subject=subject;
        this.answer=answer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
