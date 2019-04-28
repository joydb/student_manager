package com.dbstudy.springboot.manager;

import com.dbstudy.springboot.manager.dao.ClassesDao;
import com.dbstudy.springboot.manager.dao.StuDao;
import com.dbstudy.springboot.manager.dao.TeaDao;
import com.dbstudy.springboot.manager.dao.stuHomeDao;
import com.dbstudy.springboot.manager.service.ClassUtil;
import com.dbstudy.springboot.manager.service.StudentService;
import com.dbstudy.springboot.manager.service.StudentUtil;
import com.dbstudy.springboot.manager.service.TeacherUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getDatasource() {
        try {
            System.out.println(this.dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTemplate() {
        System.out.println(this.jdbcTemplate.getDataSource());
    }

    @Autowired
    private TeacherUtil teacherUtil;

    @Autowired
    private StudentUtil studentUtil;

    @Test
    public void queryTeacher(){
        TeaDao teacher=teacherUtil.queryTeacher(1);
        System.out.println(teacher);
    }
    @Test
    public void queryStudent(){
        StuDao student=studentUtil.queryStudent(1);
        System.out.println(student);
    }

    @Test
    public void testJdbc(){
        List list=teacherUtil.queryTeachers();
        Iterator iterator=list.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
    @Autowired
    private ClassUtil classUtil;
    @Test
    public void clsUtil() {
        ClassesDao cls=classUtil.queryCls(1);
        System.out.println(cls);
    }

    @Autowired
    private StudentService studentService;
    @Test
    public void getStuSer(){
        List<stuHomeDao> list=studentService.getHomTable(1);
        Iterator<stuHomeDao> iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
