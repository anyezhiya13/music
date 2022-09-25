package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.service.AdminService;
import com.gen.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:8945/")
public class AdminController {

    @Autowired
    AdminService adminService;
    /**
     * 判断是否登录成功
     */
    @ResponseBody
    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject=new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name);
        System.out.println(password);
        boolean flag=adminService.verifyPassword(name,password);
        System.out.println(flag);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"登陆成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MESSAGE,"用户名或者密码错误");
        return jsonObject;

    }
}
