package com.dbstudy.springboot.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class TeaDelHomeService {
    /*
     *  a.按编号删除班级中作业
        delete from ch_help where hom_id = 1;
        b.按编号删除学生作业
        delete from sh_help where hom_id = 1;
        c.删除homework表中作业
        delete from homework where hom_id = 1;
     */
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public void delCHHelpHom(int homId){
        String sql="delete from ch_help where hom_id = ?; ";
        this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homId);
            }
        });
    }
    public void delSHHelpHom(int homId){
        String sql="delete from sh_help where hom_id = ?; ";
        this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homId);
            }
        });
    }
    public void delHom(int homId){
        String sql="delete from homework where hom_id = ?; ";
        this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homId);
            }
        });
    }

    public void teaDelHome(int homId){
        this.delCHHelpHom(homId);
        this.delSHHelpHom(homId);
        this.delHom(homId);
    }
}
