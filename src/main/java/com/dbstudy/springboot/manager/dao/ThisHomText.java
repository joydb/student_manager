package com.dbstudy.springboot.manager.dao;

public class ThisHomText {
    private int stuId;
    private int homId;
    private String isFinish;
    private String answer;

    @Override
    public String toString() {
        return "ThisHomText{" +
                "stuId=" + stuId +
                ", homId=" + homId +
                ", isFinish='" + isFinish + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getHomId() {
        return homId;
    }

    public void setHomId(int homId) {
        this.homId = homId;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
