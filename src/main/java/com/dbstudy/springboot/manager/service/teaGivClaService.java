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
public class teaGivClaService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public void teaGiveCla(int claId,String text){
        //insert into homework values(7,"python从精通到陌生");
        //insert into ch_help values(1,7);
        //insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
        String sql1="select max(hom_id) from homework";
//        RowMapper<Integer> rowMapper=new BeanPropertyRowMapper(Integer.class);
//        Integer maxHome=this.myJdbcTemplate.queryForObject(sql1,rowMapper);
        /*
        int count = getJdbcTemplate().queryForObject(
                        sql, new Object[] { username }, Integer.class);
         */
        int maxHome=this.myJdbcTemplate.queryForObject(sql1,Integer.class);
        Integer homeId=maxHome+1;
        String sql2="insert into homework values(?,?);";
        this.myJdbcTemplate.update(sql2, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homeId);
                preparedStatement.setString(2,text);
            }
        });
        String sql3="insert into ch_help values(?,?);";
        this.myJdbcTemplate.update(sql3, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,claId);
                preparedStatement.setInt(2,homeId);
            }
        });
        String sql4="insert into sh_help(stu_id,hom_id) (select stu_id,? from student where cla_id = ?);";
        this.myJdbcTemplate.update(sql4, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homeId);
                preparedStatement.setInt(2,claId);
            }
        });

    }
}
