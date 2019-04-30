package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.TeaClaHomDao;
import com.dbstudy.springboot.manager.dao.TeaClaInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaClaInfoService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public List<TeaClaInfoDao> getTeaClaInfo(){
        String sql="select teacher.tea_id,teacher.tea_name,teacher.tea_age,teacher.tea_sex,x.cla_id,x.cla_name from \n" +
                "(select ct_help.tea_id,ct_help.cla_id,cla_name from ct_help left join classes\n" +
                "on ct_help.cla_id = classes.cla_id) as x \n" +
                "left join teacher on x.tea_id=teacher.tea_id order by teacher.tea_id;";
        RowMapper<TeaClaInfoDao> rowMapper=new BeanPropertyRowMapper<>(TeaClaInfoDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper);
    }
}
