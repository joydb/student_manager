package com.dbstudy.springboot.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping("/common/relogin")
    public String teaLogin(){
        return "login";
    }
}
