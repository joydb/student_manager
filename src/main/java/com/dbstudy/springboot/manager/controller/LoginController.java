package com.dbstudy.springboot.manager.controller;

import com.dbstudy.springboot.manager.dao.ManagerDao;
import com.dbstudy.springboot.manager.dao.StuDao;
import com.dbstudy.springboot.manager.dao.TeaDao;
import com.dbstudy.springboot.manager.service.ManagerService;
import com.dbstudy.springboot.manager.service.StudentUtil;
import com.dbstudy.springboot.manager.service.TeacherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentUtil studentUtil;
    @Autowired
    private TeacherUtil teacherUtil;

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/user/login")
    public String firstLogin(@RequestParam("id")int id,
                             @RequestParam("password") String password,
                             @RequestParam("position") String position,
                             Map<String,Object> map,
                             HttpSession session)
    {
        //1.校验id和密码
        if(StringUtils.isEmpty(id)||StringUtils.isEmpty(password)||StringUtils.isEmpty(position)) {
            map.put("msg","请输入正确的值！");
            return "login";
        }
        //防止多次登陆
        session.setAttribute("userId",id);
        logger.info("当前登陆用户:{id:"+id+"    password:"+password+"    position:"+position+"}");
        switch (position){
            case "student":{
                StuDao stuDao=studentUtil.queryStudent(id);
                if(stuDao!=null&&stuDao.getStuPass().equals(password)){
                    return "studentHome";
                }
                map.put("msg","输入有误！！！");
                break;
            }
            case "teacher":{
                TeaDao teacher=teacherUtil.queryTeacher(id);
                if(teacher!=null&&teacher.getTeaPass().equals(password)){
                    return "teaHome";
                }
                break;
            }
            case "manager":{
                ManagerDao manager=managerService.queryManagerById(id);
                if (manager!=null&&manager.getManPass().equals(password)) {
                    return "manHome";
                }
                break;
            }
        }
        return "error";
    }
}
