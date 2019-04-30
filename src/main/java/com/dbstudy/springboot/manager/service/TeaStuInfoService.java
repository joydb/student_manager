package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.StuClaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaStuInfoService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;
    public List<StuClaDao> getTeaStuInfo(){
        String sql="select stu_id,stu_name,stu_sex, stu_age,classes.cla_name \n" +
                "from student,classes where student.cla_id = classes.cla_id;";
        RowMapper<StuClaDao> rowMapper=new BeanPropertyRowMapper<>(StuClaDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper);
    }
}
