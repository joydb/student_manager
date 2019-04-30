package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.HomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class HomService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public HomDao queryHomeById(int id){
        String sql="select *  from homework where hom_id=?;";
        RowMapper<HomDao> rowMapper=new BeanPropertyRowMapper<>(HomDao.class);
        HomDao home=this.myJdbcTemplate.queryForObject(sql,rowMapper,id);
        return home;
    }

    public int insertHome(int homId,String workText){
        String sql="insert into homework values(?,?);";
        return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,homId);
                preparedStatement.setString(2,workText);
            }
        });
    }
}
