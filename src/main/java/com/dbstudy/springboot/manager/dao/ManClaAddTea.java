package com.dbstudy.springboot.manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ManClaAddTea {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    //查询

    //添加
    public int addTeaForCla(int claId,int teaId){
        String sql="insert into ct_help value (?,?);";
        return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,claId);
                preparedStatement.setInt(1,teaId);
            }
        });
    }
}
