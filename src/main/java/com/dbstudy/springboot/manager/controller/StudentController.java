package com.dbstudy.springboot.manager.controller;

import com.dbstudy.springboot.manager.dao.ClassesDao;
import com.dbstudy.springboot.manager.dao.StuDao;
import com.dbstudy.springboot.manager.dao.ThisHomText;
import com.dbstudy.springboot.manager.dao.stuHomeDao;
import com.dbstudy.springboot.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;
import java.util.Map;

/*
 *-- 学生功能
//1.查看自己的作业
-- 作业名称  是否完成  完成按钮
select homework.work_text,s.isFinish,s.answer from homework inner join
(select sh_help.hom_id,sh_help.isFinish,sh_help.answer from sh_help where stu_id = 1) as s
on s.hom_id = homework.hom_id;
//2.点击完成作业按钮，提交
insert into sh_help values(1,2,"y","这个作业我不会，你能把我怎么说");
 */

@Controller
public class StudentController {

    @Autowired
    private StudentUtil studentUtil;

    @Autowired
    private ClassUtil classUtil;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ThisHomService thisHomService;

    @Autowired
    private HomService homService;
    //1.查询学生的基本信息
    //基础表（学生）学号 姓名 性别 年龄 班级 密码
    //name stuId sex age clsName
    @RequestMapping("/student/studentInfo")
    public String stuInfo(HttpSession session, Map<String,Object> map){
        String idObj=session.getAttribute("userId").toString();
        int id=Integer.parseInt(idObj);
        StuDao student=studentUtil.queryStudent(id);
        map.put("name",student.getStuName());
        map.put("stuId",student.getStuId());
        String sex=student.getStuSex();
        if("man".equals(sex)){
            map.put("sex","男");
        }
        //性别默认为女
        map.put("sex","女");
        map.put("age",student.getStuAge());
        Integer clsId=student.getClsId();
        if(clsId!=null){
            ClassesDao classes=classUtil.queryCls(id);
            if (classes!=null){
                map.put("clsName",classes.getClaName());
            }
            else {
                map.put("clsName","为分配班级");
            }
        }
        return "studentInfo";
    }
    //2.查看所有作业
    @RequestMapping("/student/studentTable")
    public String stuTable(HttpSession session,Map<String,Object> map){
        String idObj=session.getAttribute("userId").toString();
        int id=Integer.parseInt(idObj);
        //获取此学生的作业列表  workText isFinish answer
        List<stuHomeDao>  homList=studentService.getHomTable(id);
        map.put("homTable",homList);
        return "studentTable";
    }
    //3.查看当前作业
    @RequestMapping("/student/homText")
    public String studentHom(HttpSession session,
                         int homId,
                         Map<String,Object> map){
        session.setAttribute("homId",homId);
        String tmp=session.getAttribute("userId").toString();
        int stuId=Integer.parseInt(tmp);
        ThisHomText hom=thisHomService.getThisHom(stuId,homId);
        String homText=homService.queryHomeById(homId).getWorkText();
        map.put("homText",homText);
        map.put("answer",hom.getAnswer());
        return "stuHomFrom";
    }
    //4.提交作业
    @RequestMapping("/student/homSub")
    public String subHom(HttpSession session,
                         String answer,
                       Map<String,Object> map){
        String hom=session.getAttribute("homId").toString();
        String stu=session.getAttribute("userId").toString();
        int homId=Integer.parseInt(hom);
        int stuId=Integer.parseInt(stu);
        System.out.println(answer);
        thisHomService.setThisHom(homId,stuId,answer);
        this.studentHom(session,homId,map);

        //return "redirect:/student/homText";
        //return null;



        ThisHomText home=thisHomService.getThisHom(stuId,homId);
        String homText=homService.queryHomeById(homId).getWorkText();
        map.put("homText",homText);
        map.put("answer",home.getAnswer());
        return "stuHomFrom";
    }
}
