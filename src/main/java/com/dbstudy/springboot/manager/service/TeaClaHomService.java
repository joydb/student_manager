package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.TeaClaHomDao;
import com.dbstudy.springboot.manager.dao.TeaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaClaHomService {

    @Autowired
    private JdbcTemplate myJdbcTemplate;

    //查询
    public List<TeaClaHomDao> getClaHom(int id){
        //班级id 班级名称 作业名称

        String sql="select cla_id,cla_name,work_text from classes,homework  " +
                "where cla_id in (select cla_id from ch_help where cla_id in  " +
                "(select classes.cla_id from ct_help where tea_id = ?))  and  " +
                "hom_id in (select hom_id from ch_help where cla_id in  " +
                "(select classes.cla_id from ct_help where tea_id = ?)) order by cla_id;";
        RowMapper<TeaClaHomDao> rowMapper=new BeanPropertyRowMapper<>(TeaClaHomDao.class);
        List<TeaClaHomDao> teaClaHomDaos=this.myJdbcTemplate.query(sql,rowMapper,id,id);
        return teaClaHomDaos;
    }
}
