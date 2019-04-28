package com.dbstudy.springboot.manager.dao;
//基础表（老师）老师  编号 姓名 年龄 性别 密码
public class TeaDao {
    private int teaId;
    private String teaName;
    private int teaAge;
    private String teaSex;
    private String teaPass;

    @Override
    public String toString() {
        return "TeaDao{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaAge=" + teaAge +
                ", teaSex='" + teaSex + '\'' +
                ", teaPass='" + teaPass + '\'' +
                '}';
    }

    public String getTeaPass() {
        return teaPass;
    }

    public void setTeaPass(String teaPass) {
        this.teaPass = teaPass;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public int getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(int teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }
}
