package com.dbstudy.springboot.manager.controller;

import com.dbstudy.springboot.manager.dao.*;
import com.dbstudy.springboot.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {

    @Autowired
    private TeacherUtil teacherUtil;

    @Autowired
    private TeaClaHomService teaClaHomService;

    @Autowired
    private TeaStuHomeService stuHomeService;

    @Autowired
    private TeaStuInfoService teaStuInfoService;

    @Autowired
    private TeaClaInfoService teaClaInfoService;

    //1.查询老师基本信息
    @RequestMapping("/teacher/teaInfo")
    public String getTeaInfo(HttpSession session,
                             Map<String,Object> map){
        String teaId=session.getAttribute("userId").toString();
        int id=Integer.parseInt(teaId);
        TeaDao teacher=teacherUtil.queryTeacher(id);
        map.put("name",teacher.getTeaName());
        map.put("stuId",teacher.getTeaId());
        String sex=teacher.getTeaSex();
        System.out.println("老师的性别为"+teacher.getTeaSex());
        if("man".equals(sex)){
            map.put("sex","男");
        }
        //性别默认为女
        else {
            map.put("sex","女");
        }
        map.put("age",teacher.getTeaAge());
        return "teaInfo";
    }
    //2.查询学生信息
    //stu_id 学号| stu_name 姓名 | stu_sex 性别| stu_age 年龄| cla_name 班级名称
    @RequestMapping("/teacher/student")
    public String teaStuInfo(Map<String,Object> map){
        List<StuClaDao> stuInfo=teaStuInfoService.getTeaStuInfo();
        map.put("stuInfo",stuInfo);
        return "teaStuInfo";
    }
    //3.查询班级信息
    @RequestMapping("/teacher/claInfo")
    public String teaClaInfo(Map<String,Object> map){
        List<TeaClaInfoDao> claInfo=this.teaClaInfoService.getTeaClaInfo();
        map.put("claInfo",claInfo);
        return "teaClaInfo";
    }
    //4.查询班级大作业
    @RequestMapping("/teacher/claHome")
    public String teaClaHome(HttpSession session,
                             Map<String,Object> map){
        String teaId=session.getAttribute("userId").toString();
        int id=Integer.parseInt(teaId);
        List<TeaClaHomDao> teaClaHoms=this.teaClaHomService.getClaHom(id);
        map.put("teaClaHom",teaClaHoms);
        return "teaClaHome";
    }

    //5.查询个人作业
    @RequestMapping("/teacher/stuHome")
    public String teaStuHome(HttpSession session,
                             Map<String,Object> map){
        //学生信息
        //学生学号 学生姓名 学生性别 查看详情
        String teaId=session.getAttribute("userId").toString();
        int id=Integer.parseInt(teaId);
        List<StuDao> stuHome=this.stuHomeService.getTeaStuHom(id);
        System.out.println(stuHome);

        map.put("stuHom",stuHome);
        return "teaStuHome";
    }
    //6.返回主页
    @RequestMapping("/teacher/home")
    public String reTeaHome(){
        return "teaHome";
    }
}
