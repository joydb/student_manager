package com.dbstudy.springboot.manager.dao;

public class stuHomeDao {
    private int homId;
    private String workText;
    private String isFinish;
    private String answer;

    public String getWorkText() {
        return workText;
    }

    public void setWorkText(String workText) {
        this.workText = workText;
    }

    @Override
    public String toString() {
        return "stuHomeDao{" +
                "homId=" + homId +
                ", workText='" + workText + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", answer='" + answer + '\'' +
                '}';
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

    public int getHomId() {
        return homId;
    }

    public void setHomId(int homId) {
        this.homId = homId;
    }
}
