package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.StuIsFinishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuIsFinishService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public List<StuIsFinishDao> getStuIsFinish(int id){
       String sql="select stu_name,isFinish,answer from student,sh_help\n" +
               "where student.stu_id = 1 and sh_help.stu_id=?;";
        RowMapper<StuIsFinishDao> rowMapper=new BeanPropertyRowMapper<>(StuIsFinishDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper,id);
    }

}
