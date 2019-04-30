package com.dbstudy.springboot.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeaForHomController {
    @RequestMapping("/teacher/setStuHom")
    public String setStuHom(int stuId){
        //insert into homework values(7,"python从精通到陌生");
        //insert into ch_help values(1,7);
        //insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
        //添加作业编号  作业内容
        //添加到作业学生表
        return null;
    }
    public String setClaHom(int stuId){
        //insert into homework values(7,"python从精通到陌生");
        //insert into ch_help values(1,7);
        //insert into sh_help(stu_id,hom_id) (select stu_id,7 from student where cla_id = 1);
        //添加作业编号  作业内容
        //添加到作业学生表
        //添加到班级作业表
        return null;
    }
}
