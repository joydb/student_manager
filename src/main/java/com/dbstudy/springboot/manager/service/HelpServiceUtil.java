package com.dbstudy.springboot.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class HelpServiceUtil {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    //insert into ch_help values(1,7);
    //insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);

    public int insertIntoChHelp(int stuId,
                                int homId,
                                String isFinish,
                                String answer){
        String sql="insert into sh_help value(?,?,?,?);";
        return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,stuId);
                preparedStatement.setInt(2,homId);
                preparedStatement.setString(3,isFinish);
                preparedStatement.setString(4,answer);
            }
        });
    }
    public int insertIntoShHelp(){
        return 0;
    }
}
