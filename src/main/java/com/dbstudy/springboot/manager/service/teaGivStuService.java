package com.dbstudy.springboot.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
@Service
public class teaGivStuService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public void teaGivStuService(int stuId,String text){
//        String sql1="select max(hom_id) from homework";
//        RowMapper<Integer> rowMapper=new BeanPropertyRowMapper(Integer.class);
//        Integer maxHome=this.myJdbcTemplate.queryForObject(sql1,rowMapper);
        String sql1="select max(hom_id) from homework";
        Integer maxHome;
        maxHome=this.myJdbcTemplate.queryForObject(sql1,Integer.class);
        if(maxHome==null){
            maxHome=0;
        }
        Integer homeId=maxHome+1;
        String sql2="insert  into homework value (?,?)";
        this.myJdbcTemplate.update(sql2, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homeId);
                preparedStatement.setString(2,text);
            }
        });
        String sql3="insert into sh_help value (?,?,?,?);";
        this.myJdbcTemplate.update(sql3, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,stuId);
                preparedStatement.setInt(2,homeId);
                preparedStatement.setString(3,"n");
                preparedStatement.setString(4,null);
            }
        });
    }
}
