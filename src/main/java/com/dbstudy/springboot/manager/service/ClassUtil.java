package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.ClassesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ClassesDao> queryAllCls(){
        String sql="select * from classes";
        RowMapper<ClassesDao> rowMapper=new BeanPropertyRowMapper<>(ClassesDao.class);
        List<ClassesDao> classes=this.myJdbcTemplate.query(sql,rowMapper);
        return classes;
    }
    public List<ClassesDao> queryClsForTea(int id){
        String sql=" select cla_name,cla_id from classes where cla_id in\n" +
                "                (select cla_id from ct_help where tea_id = ?);";
        RowMapper<ClassesDao> rowMapper=new BeanPropertyRowMapper<>(ClassesDao.class);
        List<ClassesDao> classes=this.myJdbcTemplate.query(sql,rowMapper,id);
        return classes;
    }
}
