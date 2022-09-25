package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.Consumer;
import com.gen.music.service.ConsumerService;
import com.gen.music.utils.Consts;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前端用户控制类
 */
@RestController
@RequestMapping("/consumer")
@CrossOrigin(origins = "http://localhost:8945/")
public class ConsumerController {


    @Resource
    ConsumerService consumerService;

    /**
     * 添加前端用户
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();  //账号
        String password = request.getParameter("password").trim();   //密码
        String sex = request.getParameter("sex").trim();  // 性别
        String phoneNum = request.getParameter("phoneNum").trim(); //手机号
        String email = request.getParameter("email").trim();  //电子邮箱
        String birth = request.getParameter("birth").trim();   //生日
        String introduction = request.getParameter("introduction").trim(); //签名
        String location = request.getParameter("location").trim();  //地区
        String avatar = request.getParameter("avatar").trim();  // 头像
        if (username==null||username.equals("")){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "用户名不能为空");
            return jsonObject;
        }
        Consumer consumer1 = consumerService.getByUsername(username);
        if (consumer1!=null){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "用户名已存在");
            return jsonObject;
        }
        if (password==null||password.equals("")){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "密码不能为空");
            return jsonObject;
        }
        // 把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvatar(avatar);
        boolean flag = consumerService.insert(consumer);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "添加失败");
        return jsonObject;
    }

    /**
     * 修改前端用户
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区

        if(username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MESSAGE,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MESSAGE,"修改失败");
        return jsonObject;
    }
    /*
    删除前端用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Consumer consumer = consumerService.selectByPrimaryKey(Integer.parseInt(id));
        String pic = consumer.getAvatar();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        File file = new File(filePath + pic);
        //判断此文件是否为空
        if (file != null) {
            //文件不为空，执行删除
            file.delete();
        } else {
            //为空提示错误信息
            request.setAttribute("error", "文件不存在，不能删除");
        }
        boolean flag = consumerService.delete(Integer.parseInt(id));
        System.out.println(flag);
        return flag;
    }

    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));

    }

    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/allConsumer", method = RequestMethod.GET)
    public Object allConsumer() {
        System.out.println(consumerService.allConsumer());
        return consumerService.allConsumer();
    }

    /**
     * 更新前端用户头像图片
     */
    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile avatarFile) {
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败");
            return jsonObject;
        }
        Consumer consumer1 = consumerService.selectByPrimaryKey(id);
        String avatar = consumer1.getAvatar();
        //文件名=当前时间到毫米+原来的文件名
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatarImages";
        String filePath1 = System.getProperty("user.dir");
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
//        实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        File file1 = new File(filePath1 + avatar);
//        储存到数据库的文件地址
        String storeAvatorPath = "/avatarImages/" + fileName;
        try {
            avatarFile.transferTo(desk);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if (flag) {
                //判断此文件是否为空
                file1.delete();
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "上传成功");
                jsonObject.put("avatar",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }
    /**
     *前端用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();  //账号
        String password = request.getParameter("password").trim();   //密码
        if (username==null||username.equals("")){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "用户名不能为空");
            return jsonObject;
        }
        if (password==null||password.equals("")){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "密码不能为空");
            return jsonObject;
        }

        //保存到前端用户对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = consumerService.verifyPassword(username,password);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "登录成功");
            jsonObject.put("userMsg",consumerService.getByUsername(username));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "用户名或者密码错误");
        return jsonObject;
    }

}
