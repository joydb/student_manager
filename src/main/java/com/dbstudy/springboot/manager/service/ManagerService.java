package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ManagerService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public ManagerDao queryManagerById(int id){
        String sql="select * from manager where id=?;";
        RowMapper<ManagerDao> rowMapper=new BeanPropertyRowMapper(ManagerDao.class);
        return this.myJdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public int addTeaForCla(int claId,int teaId){
        String sql="insert into ct_help value (?,?);";
        return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,claId);
                preparedStatement.setInt(2,teaId);
            }
        });
    }
}
