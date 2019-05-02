package com.dbstudy.springboot.manager.dao;

public class StuIsFinishDao {
    //stu_name | isFinish  | answer
    private String stuName;
    private String isFinish;
    private String answer;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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
