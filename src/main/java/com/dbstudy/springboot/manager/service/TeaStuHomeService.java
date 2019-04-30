package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.StuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeaStuHomeService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public List<StuDao> getTeaStuHom(int id){
        String sql="  select stu_id,stu_name, stu_sex from student where cla_id in (\n" +
                "    select cla_id from ct_help where tea_id = ?\n" +
                "  );";
        RowMapper<StuDao> rowMapper=new BeanPropertyRowMapper(StuDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper,1);
    }
}
