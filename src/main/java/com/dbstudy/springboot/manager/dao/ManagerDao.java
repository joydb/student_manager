package com.dbstudy.springboot.manager.dao;

public class ManagerDao {
    //id | man_name | man_pass | man_age | man_sex
    private int id;
    private String manName;
    private String manPass;
    private int manAge;
    private String manSex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getManPass() {
        return manPass;
    }

    public void setManPass(String manPass) {
        this.manPass = manPass;
    }

    public int getManAge() {
        return manAge;
    }

    public void setManAge(int manAge) {
        this.manAge = manAge;
    }

    public String getManSex() {
        return manSex;
    }

    public void setManSex(String manSex) {
        this.manSex = manSex;
    }
}
