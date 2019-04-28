package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.ClassesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassUtil {

    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public ClassesDao queryCls(int id){

        String sql="select * from classes where cla_id=?;";
        RowMapper<ClassesDao> rowMapper=new BeanPropertyRowMapper<>(ClassesDao.class);
        ClassesDao classes=this.myJdbcTemplate.queryForObject(sql,rowMapper,id);
        return classes;
    }
}
