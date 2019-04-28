package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.HomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

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
}
