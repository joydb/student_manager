package com.dbstudy.springboot.manager.service;

import com.dbstudy.springboot.manager.dao.TeaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherUtil {

    @Autowired
    private JdbcTemplate myJdbcTemplate;

    //基础表（老师）老师
    // 编号 姓名 性别 年龄  密码
    //插入
    public int addTeacher(TeaDao teacher) {
        String sql="insert into teacher values(?,?,?,?,?);";
//        List<Object> list=new ArrayList<>();
//        list.add(new Object[]{
//                teacher.getTeaId(),
//                teacher.getTeaName()});
        int result=this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,teacher.getTeaId());
                preparedStatement.setString(2,teacher.getTeaName());
                preparedStatement.setString(3,teacher.getTeaSex());
                preparedStatement.setInt(4,teacher.getTeaAge());
                preparedStatement.setString(5,teacher.getTeaPass());
            }
        });
        if(result==0) {
            System.out.println("插入失败");
        }
        return result;
    }
    //删除
    public int deleteTeacher(int id){
        String sql="delete from teacher where id = ?;";
        return this.myJdbcTemplate.update(sql,id);
    }
    //修改
    public int modifyTeacher(TeaDao teacher,int id){
        String sql="update teacher  set tea_id=? ,tea_name=?, tea_sex=?,tea_age=?,tea_pass=? where tea_id=?;";
        int result=this.myJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,teacher.getTeaId());
                preparedStatement.setString(2,teacher.getTeaName());
                preparedStatement.setString(3,teacher.getTeaSex());
                preparedStatement.setInt(4,teacher.getTeaAge());
                preparedStatement.setString(5,teacher.getTeaPass());
                preparedStatement.setInt(6,id);
            }
        });
        if(result==0) {
            System.out.println("插入失败");
        }
        return result;
    }
    //查询一个
    public TeaDao queryTeacher(int id){
        String sql="select * from teacher where tea_id=?";
        /*
                jdbcTemplate.queryForObject(sql, new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet rs, int i)
                    throws SQLException {
                Customer c = new Customer();
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                return c;
            }
 */
        RowMapper<TeaDao> rowMapper=new BeanPropertyRowMapper<>(TeaDao.class);
        TeaDao teaDao=this.myJdbcTemplate.queryForObject(sql,rowMapper,id);
        return teaDao;
    }
    //RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
    public List<TeaDao> queryTeachers(){
             /*
         *   //RowMapper是一个接口,这里我们使用其子类
                RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
                //该query方法查询出来的是一个list列表,query方法的最后一个参数是可变参数！
                List<Employee> list = template.query(sql, rowMapper, 4000);

                for (Employee employee : list) {
                    System.out.println(employee);
                }
         */
             String sql="select * from teacher;";
             RowMapper<TeaDao> rowMapper=new BeanPropertyRowMapper<>(TeaDao.class);
             List<TeaDao> teaDaos=this.myJdbcTemplate.query(sql,rowMapper);
        return teaDaos;
    }
}
