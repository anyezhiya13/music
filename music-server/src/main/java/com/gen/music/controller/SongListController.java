package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.SongList;
import com.gen.music.service.SongListService;
import com.gen.music.utils.Consts;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌单列表控制类
 */
@RestController
@RequestMapping("/songList")
@CrossOrigin(origins = "http://localhost:8945/")
public class SongListController {


    @Resource
    SongListService songListService;

    /**
     * 添加歌单列表
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String title = request.getParameter("title").trim();  //标题
        String pic = request.getParameter("pic").trim();   //图片
        String introduction = request.getParameter("introduction").trim();   //简介
        String style = request.getParameter("style").trim();  // 风格

        //保存到歌单列表对象中
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
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
     * 修改歌单列表
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();  //标题
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();  // 风格
        //保存到歌单列表对象中
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.update(songList);
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
    删除歌单列表
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        SongList SongList = songListService.selectByPrimaryKey(Integer.parseInt(id));
        String pic = SongList.getPic();
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
        boolean flag = songListService.delete(Integer.parseInt(id));
        System.out.println(flag);
        return flag;
    }
    /**
     * 根据主键查询整个对象
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return songListService.selectByPrimaryKey(Integer.parseInt(id));

    }
    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/allSongList",method = RequestMethod.GET)
    public Object allSongList(HttpServletRequest request){
        return songListService.allSongList();
    }
    /**
     * 根据歌单列表根据标题精确查询
     */
    @RequestMapping(value = "/songListOfTitle",method =RequestMethod.GET)
    public Object songListOfTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.songListOfTitle(title);
    }
    /**
     * 根据歌单列表根据标题模糊查询
     */
    @RequestMapping(value = "/likeTitle",method =RequestMethod.GET)
    public Object likeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.likeTitle("%"+title+"%");
    }
    /**
     * 根据歌单风格模糊查询
     */
    @RequestMapping(value = "/likeStyle",method =RequestMethod.GET)
    public Object likeStyle(HttpServletRequest request){
        String style= request.getParameter("style").trim();
        return songListService.likeStyle("%"+style+"%");
    }
    /**
     * 更新头像图片
     */
    @RequestMapping(value = "/updateSongListPic",method =RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile avatorFile){
        JSONObject jsonObject=new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败");
            return jsonObject;
        }
        SongList songList1 = songListService.selectByPrimaryKey(id);
        String pic = songList1.getPic();
        //文件名=当前时间到毫米+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        String filePath1=System.getProperty("user.dir");
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
//        实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
//        String s = filePath1 + pic;
//        System.out.println(s);
        File file1 = new File(filePath1 + pic);
//        储存到数据库的文件地址
        String storeAvatorPath="/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(desk);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag=songListService.update(songList);
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
