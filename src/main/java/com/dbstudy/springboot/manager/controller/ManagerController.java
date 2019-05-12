package com.dbstudy.springboot.manager.controller;

import com.dbstudy.springboot.manager.dao.*;
import com.dbstudy.springboot.manager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TeacherUtil teacherUtil;

    @Autowired
    private ClassUtil classUtil;

    @Autowired
    private TeaStuInfoService teaStuInfoService;

    @Autowired
    private StudentUtil studentUtil;

    //1.回到菜单
    @RequestMapping("/manager/home")
    public String goHome(){
        return "manHome";
    }
    //2.查询个人基本信息
    @RequestMapping("/manager/manInfo")
    public String getManInfo(HttpSession session,
                             Map<String,Object> map){
        String manId=session.getAttribute("userId").toString();
        int id=Integer.parseInt(manId);
        ManagerDao manager=managerService.queryManagerById(id);
        map.put("id",manager.getId());
        map.put("name",manager.getManName());
        map.put("age",manager.getManAge());
        map.put("sex",manager.getManSex());
        return "manInfo";
    }
    //3.增加老师页面
    @RequestMapping("/manager/addtea")
    public String addTeacher(){
        return "manTeaAdd";
    }
    //4.增加老师提交
    @RequestMapping("/manager/addTeaSub")
    public String addTeacherSub(String id,
                                String name,
                                String age,
                                String sex,
                                String password,
                                Map<String,Object> map){
        Logger logger=LoggerFactory.getLogger(this.getClass());
        logger.info("增加老师：{id:"+id+"name:"+name+"age:"+age+"sex:"+sex+"password"+password);
        try{
            //
//        private int teaId;
//    private String teaName;
//    private int teaAge;
//    private String teaSex;
//    private String teaPass;
            int teaId=Integer.parseInt(id);
            int teaAge=Integer.parseInt(age);
            TeaDao teacher=new TeaDao();
            teacher.setTeaId(teaId);
            teacher.setTeaAge(teaAge);
            teacher.setTeaName(name);
            teacher.setTeaPass(password);
            teacher.setTeaSex(sex);
            teacherUtil.addTeacher(teacher);
        }catch (Exception exception){
            map.put("msg","输入有误");
        }
        return this.addTeacher();
    }
    //5.查看所有老师
    @RequestMapping("/manager/queryAllTea")
    public String queryAllTeacher(Map<String,Object> map){
        List<TeaDao> teachers = teacherUtil.queryTeachers();
        map.put("teachers",teachers);
        return "manAllTeacher";
    }
    //删除老师
    @RequestMapping("/manager/delTea")
    public String manDelTea(int teaId,
                            Map<String,Object> map){
        teacherUtil.deleteTeacher(teaId);
        return this.queryAllTeacher(map);
    }
    //6.查看所有班级
    @RequestMapping("/manager/allClasses")
    public String queryAllClass(Map<String,Object> map){
        List<ClassesDao> list=classUtil.queryAllCls();
        map.put("allClass",list);
        return "manAllClasses";
    }
    //7.为班级分配老师
    @RequestMapping("/manager/teaAddCla")
    public String teaAddCla(Map<String,Object> map){
        List<ClassesDao> clasList=classUtil.queryAllCls();
        List<TeaDao> teaList = teacherUtil.queryTeachers();
        map.put("clasList",clasList);
        map.put("teaList",teaList);
        return "manClaAddTeacher";
    }
    //8.分配后提交
    @RequestMapping("/manager/teaAddClaSub")
    public String teaAddClaSub(int claId,
                               int teaId,
                               Map<String,Object> map){
        System.out.println("班级Id:"+claId+"   老师Id:"+teaId);
        managerService.addTeaForCla(claId,teaId);
        return this.queryAllClass(map);
    }
    //8.添加班级

    //9.增加学生
    @RequestMapping("/manager/addStudent")
    public String manAddStudent(Map<String,Object> map){
        List<ClassesDao> claList=classUtil.queryAllCls();
        map.put("allClass",claList);
        return "manStuAdd";
    }
    //9.1增加学生提交
    //10.增加老师提交
    @RequestMapping("/manager/addSruSub")
    public String addStudentSub(String id,
                                String name,
                                String age,
                                String sex,
                                String password,
                                int claId,
                                Map<String,Object> map){
        Logger logger=LoggerFactory.getLogger(this.getClass());
        logger.info("增加学生：{id:"+id+"name:"+name+"age:"+age+"sex:"+sex+"password"+password+"claId"+claId);
        try{
            int stuId=Integer.parseInt(id);
            int stuAge=Integer.parseInt(age);
            StuDao student=new StuDao();
            student.setStuId(stuId);
            student.setStuName(name);
            student.setStuSex(sex);
            student.setStuAge(stuAge);
            student.setClsId(claId);
            student.setStuPass(password);
            studentUtil.addStudent(student);
        }catch (Exception exception){
            map.put("msg","输入有误");
        }
        return this.addTeacher();
    }


    //10.查看学生
    @RequestMapping("/manager/allStudent")
    public String manAllStudent(Map<String,Object> map){
        List<StuClaDao> stuInfo=teaStuInfoService.getTeaStuInfo();
        map.put("stuInfo",stuInfo);
        return "manAllStudent";
    }
    //删除学生
    @RequestMapping("/manager/delStudent")
    public String manDelStu(int stuId,
                            Map<String,Object> map){
        studentUtil.deleteStudent(stuId);
        return this.manAllStudent(map);
    }
}
