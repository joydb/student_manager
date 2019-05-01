package com.dbstudy.springboot.manager.dao;
// stu_id | hom_id | isFinish | answer
public class SHhelpDao {
    private int stuId;
    private int homId;
    private String stuName;
    private String isFinish;
    private String answer;


    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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
