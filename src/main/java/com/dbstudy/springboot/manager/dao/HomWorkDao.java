package com.dbstudy.springboot.manager.dao;
//基础表（作业） 作业id 作业内容
public class HomWorkDao {
    private int HomId;
    private String WorkText;

    public int getHomId() {
        return HomId;
    }

    public void setHomId(int homId) {
        HomId = homId;
    }

    public String getWorkText() {
        return WorkText;
    }

    public void setWorkText(String workText) {
        WorkText = workText;
    }
}
