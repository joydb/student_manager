package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.stuHomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    //查询一个学生的作业表
    //作业名称  是否完成 完成按钮 查看答案
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public List<stuHomeDao>  getHomTable(int id){
//        String sql="select homework.work_text,s.isFinish,s.answer from homework inner join  " +
//                "(select sh_help.hom_id,sh_help.isFinish,sh_help.answer from sh_help where stu_id = ?) as s " +
//                "on s.hom_id = homework.hom_id;";
        String sql="select homework.hom_id,homework.work_text,s.isFinish,s.answer from homework inner join   " +
                "(select sh_help.hom_id,sh_help.isFinish,sh_help.answer from sh_help where stu_id = ?) as s  " +
                "on s.hom_id = homework.hom_id;";
        RowMapper<stuHomeDao> rowMapper=new BeanPropertyRowMapper<>(stuHomeDao.class);
        List<stuHomeDao> stuHoms=this.myJdbcTemplate.query(sql,rowMapper,id);
        return stuHoms;
    }
}
