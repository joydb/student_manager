package com.dbstudy.springboot.manager.controller;

import com.dbstudy.springboot.manager.dao.ClassesDao;
import com.dbstudy.springboot.manager.dao.SHhelpDao;
import com.dbstudy.springboot.manager.dao.StuDao;
import com.dbstudy.springboot.manager.dao.TeaClaHomDao;
import com.dbstudy.springboot.manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
//insert into homework values(7,"python从精通到陌生");
//insert into ch_help values(1,7);
//insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
//添加作业编号  作业内容
//添加到作业学生表


//insert into homework values(7,"python从精通到陌生");
//insert into ch_help values(1,7);
//insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
//添加作业编号  作业内容
//添加到作业学生表
//添加到班级作业表


@Controller
public class TeaForHomController {

    @Autowired
    private TeaOneStuHomService teaOneStuHomService;

    @Autowired
    private HomService homService;

    @Autowired
    private ClassUtil classUtil;

    @Autowired
    private teaGivClaService teaGivClaService;

    @Autowired
    private StudentUtil studentUtil;

    @Autowired
    private teaGivStuService teaGivStuService;
    private Integer claId;
    private Integer stuId;
    //1.为学生批改作业
    @RequestMapping("/teacher/setStuHom")
    public String setStuHom(int stuId,
                            Map<String,Object> map){
        //1.通过学生id获取学生的答案
        List<SHhelpDao> stuHomes=this.teaOneStuHomService.getOneStuHome(stuId);
        map.put("stuHomes",stuHomes);
        return "teaSetStu";
    }

    //批改学生作业
    @RequestMapping("/teacher/finishStu")
    //学生的关于作业的信息
    public String finishCorrection(SHhelpDao stuHome,
                                   SHhelpDao info,
                                   int stuId,
                                   int homId,
                                   String isFinish,
                                   Map<String,Object> map){
        System.out.println("我是homID:"+homId);
        //将批改信息更新至数据库
        //stu_id | hom_id | isFinish
        //this.teaOneStuHomService.insertStuHome(stuHome.getStuId(),stuHome.getHomId(),isFinish);
        //this.teaOneStuHomService.insertStuHome(info.getStuId(),info.getHomId(),isFinish);
        this.teaOneStuHomService.insertStuHome(stuId,homId,isFinish);
        return this.setStuHom(stuId,map);
    }
    //按班级批改作业
    @RequestMapping("/teacher/setClaHom")
    public String setClaHom(TeaClaHomDao claHomD,
                            Map<String,Object> map){
        //根据班级展示此次作业
        /*
         *  // stu_id, stu_name, isFinish,
                select a.stu_id,a.stu_name,hom_id,isFinish from sh_help,
                (select stu_id,stu_name from student where cla_id = 1) as a
                where a.stu_id=sh_help.stu_id;
         */
        List<SHhelpDao> stuHomes=this.teaOneStuHomService.getClaHome(claHomD.getClaId());
        map.put("stuHome",stuHomes);
        return "teaSetCla";
    }
    //布置作业
    //1.按班级布置作业
    //2.按学生布置作业
    @RequestMapping("/teacher/givClaHom")
    public String givClaHome(HttpSession session,
                                Map<String,Object> map){
        /*
        //根据老师id找到他所带的班级
        班级名称 班级编号
                select cla_name,cla_id from classes where cla_id in
                (select cla_id from ct_help where tea_id = 1);
         */
        String id=session.getAttribute("userId").toString();
        int teaId=Integer.parseInt(id);
        List<ClassesDao> teaForCla=classUtil.queryClsForTea(teaId);
        map.put("teaForCla",teaForCla);
        return "teaClaTable";
    }
    //返回布置作业页面
    @RequestMapping("/teacher/homPage")
    public  String givTeaClaHome(int claId)
    {
        this.claId=claId;
        return "teaGivClaHome";
    }
    //根据班级Id来布置作业
    @RequestMapping("/teacher/homSub")
    public String givClaHome(String homeWork){
        /*
            insert into homework values(7,"python从精通到陌生");
            insert into ch_help values(1,7);
            insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
         */
        if (this.claId==null){
            System.out.println("claId为空");
            return null;
        }
        this.teaGivClaService.teaGiveCla(claId,homeWork);
        return "teaGivClaHome";
    }

    //按学生布置作业
    @RequestMapping("/teacher/givStuHom")
    public String givStuHom(Map<String,Object> map){
        //返回学生列表
        List<StuDao> stuDaos=studentUtil.queryStudents();
        map.put("stuTable",stuDaos);
        return "teaStuTabHome";
    }
    @RequestMapping("/teacher/givOneStuHom")
    public String givOneStudent(int stuId,
                                Map<String,Object> map){
        this.stuId=stuId;
        //map.put("stuId",stuId);
        return "teaGIvStuHom";
    }
    @RequestMapping("/teacher/stuHomSub")
    public String givOneStuSub(
                               String homeWork,
                               Map<String,Object> map){
        String temp=stuId.toString();
        System.out.println("我是temp"+temp);
        int id=Integer.parseInt(temp);
        System.out.println("我是stuID"+id);
        this.teaGivStuService.teaGivStuService(this.stuId,homeWork);
        return this.givOneStudent(this.stuId,map);
    }
}
