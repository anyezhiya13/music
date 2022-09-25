package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.Singer;
import com.gen.music.service.SingerService;
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
 * 歌手控制类
 */
@RestController
@RequestMapping("/singer")
@CrossOrigin(origins = "http://localhost:8945/")
public class SingerController {


    @Resource
    SingerService singerService;

    /**
     * 添加歌手
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setPic(pic);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手对象中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "修改失败");
        return jsonObject;
    }
    /*
    删除歌手
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Singer singer = singerService.selectByPrimaryKey(Integer.parseInt(id));
        String pic = singer.getPic();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
        File file = new File(filePath+pic);
        //判断此文件是否为空
        if(file!=null){
            //文件不为空，执行删除
            file.delete();
        }else {
            //为空提示错误信息
            request.setAttribute("error","文件不存在，不能删除");
        }
        boolean flag = singerService.delete(Integer.parseInt(id));
        System.out.println(flag);
        return flag;
    }
    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return singerService.selectByPrimaryKey(Integer.parseInt(id));

    }
    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/allSinger",method = RequestMethod.GET)
    public Object allSinger(HttpServletRequest request){
        System.out.println(singerService.allSinger());
        return singerService.allSinger();
    }
    /**
     * 根据歌手名字模糊查询
     */
    @RequestMapping(value = "/singerOfName",method =RequestMethod.GET)
    public Object singerOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return singerService.singerOfName("%"+name+"%");
    }
    /**
     * 根据歌手性别
     */
    @RequestMapping(value = "/singerOfSex",method =RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    /**
     * 更新头像图片
     */
    @RequestMapping(value = "/updateSingerPic",method =RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile avatorFile){
        JSONObject jsonObject=new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败");
            return jsonObject;
        }
        Singer singer1 = singerService.selectByPrimaryKey(id);
        String pic = singer1.getPic();
        //文件名=当前时间到毫米+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        String filePath1=System.getProperty("user.dir");
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
//        实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        String s = filePath1 + pic;
//        System.out.println(s);
        File file1 = new File(filePath1 + pic);
//        储存到数据库的文件地址
        String storeAvatorPath="/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(desk);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag=singerService.update(singer);
            if (flag){
                //判断此文件是否为空
                file1.delete();
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }

    }

}
