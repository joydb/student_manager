package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.ThisHomText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class ThisHomService {
    @Autowired
    private JdbcTemplate myJdbcTemplate;

    public ThisHomText getThisHom(int stuId,int homId){
         String sql="select * from sh_help where stu_id=? and hom_id=?;";
         RowMapper<ThisHomText> rowMapper=new BeanPropertyRowMapper<>(ThisHomText.class);
         ThisHomText homText=this.myJdbcTemplate.queryForObject(sql,rowMapper,stuId,homId);
         return homText;
     }

     public int setThisHom(int stuId,int homId,String answer){
        //stu_id  hom_id  isFinish  answer
        String sql="update  sh_help set answer=? where stu_id=? and hom_Id=?;";
         return this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
             @Override
             public void setValues(PreparedStatement preparedStatement) throws SQLException {
                 preparedStatement.setString(1,answer);
                 preparedStatement.setInt(2,stuId);
                 preparedStatement.setInt(3,homId);
             }
         });
     }
}
