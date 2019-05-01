package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.SHhelpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class TeaOneStuHomService {

    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public List<SHhelpDao> getOneStuHome(int id){
        //stu_id | hom_id | isFinish | answer
        String sql="select stu_id,hom_id,isFinish,answer from sh_help where stu_id=?;";
        RowMapper<SHhelpDao> rowMapper=new BeanPropertyRowMapper<>(SHhelpDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper,id);
    }
    //stu_id | hom_id | isFinish
    public int insertStuHome(int stuId,
                              int homId,
                              String isFinish){
        String sql="update sh_help set isFinish=? where stu_id=? and hom_id=?;";
        System.out.println("isFinist:"+isFinish+"     hom_id:"+homId+"          stuId:"+stuId);
        return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,isFinish);
                preparedStatement.setInt(2,stuId);
                preparedStatement.setInt(3,homId);
            }
        });
    }
    //根据班级查找作业
    public List<SHhelpDao> getClaHome(int id){
        String sql="select a.stu_id,a.stu_name,hom_id,isFinish from sh_help, \n" +
                "(select stu_id,stu_name from student where cla_id = ?) as a\n" +
                "where a.stu_id=sh_help.stu_id;";
        RowMapper<SHhelpDao> rowMapper=new BeanPropertyRowMapper<>(SHhelpDao.class);
        return this.myJdbcTemplate.query(sql,rowMapper,id);
    }
}
