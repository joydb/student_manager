package com.dbstudy.springboot.manager.dao;

// 基础表（班级）班级id 班级名称
public class ClassesDao {
    private int claId;
    private String claName;

    @Override
    public String toString() {
        return "ClassesDao{" +
                "claId=" + claId +
                ", claName='" + claName + '\'' +
                '}';
    }

    public int getClaId() {
        return claId;
    }

    public void setClaId(int claId) {
        this.claId = claId;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }
}
