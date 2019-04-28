package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.StuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Service
public class StudentUtil {
   @Autowired
    private JdbcTemplate myJdbcTemplate;

   //1.增加
   // 基础表（学生）
   // 学号 姓名 性别 年龄 班级 密码
    public int addStudent(StuDao student){
        String sql="insert into student values(?,?,?,?,?,?)";
        int result=this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,student.getStuId());
                preparedStatement.setString(2,student.getStuName());
                preparedStatement.setString(3,student.getStuSex());
                preparedStatement.setInt(4,student.getStuAge());
                preparedStatement.setInt(5,student.getClsId());
                preparedStatement.setString(6,student.getStuPass());
            }
        });
        if(result==0) {
            System.out.println("学生数据插入失败！！！");
        }
        return result;
    }
    //2.删除
    public int deleteStudent(int id){
        String sql="delete from student where id=?";

        return this.myJdbcTemplate.update(sql,id);
    }
    //3.修改
    public int updateStudent(StuDao student,int id){
        String sql="update student  set stu_id=? ,stu_name=?, stu_sex=?,stu_age=?,cls_id=?,stu_pass=? where stu_id=?;";
        int result=this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,student.getStuId());
                preparedStatement.setString(2,student.getStuName());
                preparedStatement.setString(3,student.getStuSex());
                preparedStatement.setInt(4,student.getStuAge());
                preparedStatement.setInt(5,student.getClsId());
                preparedStatement.setString(6,student.getStuPass());
                preparedStatement.setInt(7,id);
            }
        });
        return result;
    }
    //4.查询一个
    public StuDao queryStudent(int id){
        String sql="select * from student where stu_id=?";
        RowMapper<StuDao> rowMapper=new BeanPropertyRowMapper<>(StuDao.class);
        StuDao student=this.myJdbcTemplate.queryForObject(sql,rowMapper,id);
        return student;
    }
    //5.查询所有
    public List<StuDao> queryStudents(){
        String sql="select *  from student;";
        RowMapper<StuDao> rowMapper=new BeanPropertyRowMapper<>(StuDao.class);
        List<StuDao> list=this.myJdbcTemplate.query(sql,rowMapper);
        return list;
    }
}
