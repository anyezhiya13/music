package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.Song;
import com.gen.music.service.SongService;
import com.gen.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌曲管理
 */
@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "http://localhost:8945/")
public class SongController {


    @Autowired
    SongService songService;


    /**
     * 添加歌曲
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String singerId = request.getParameter("singerId").trim();  //所属歌手id
        String name = request.getParameter("name").trim();          //歌名
        String introduction = request.getParameter("introduction").trim();          //简介
        String pic = "/img/songPic/tubiao.jpg";                     //默认图片
        String lyric = request.getParameter("lyric").trim();     //歌词
        //上传歌曲文件
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            System.out.println(storeUrlPath);
            boolean flag = songService.insert(song);
//            System.out.println("==============" + flag);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "保存成功");
                jsonObject.put("avator", storeUrlPath);
                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "保存失败");
        return jsonObject;
    }
       /**
     * 根据歌手id查询歌曲
     */
    @RequestMapping(value = "/singer/detail",method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest request){
        String singerId = request.getParameter("singerId").trim();
        List<Song> songs = songService.songOfSingerId(Integer.parseInt(singerId));
//        System.out.println("==============song============="+songs);
        return songService.songOfSingerId(Integer.parseInt(singerId));

    }

    /**
     * 修改歌曲
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();  //主键
        String name = request.getParameter("name").trim();    //歌名
        String introduction = request.getParameter("introduction").trim();   //简介（专辑）
        String lyric = request.getParameter("lyric").trim();    //歌词
        //保存到歌手对象中
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag = songService.update(song);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MESSAGE, "修改失败");
        return jsonObject;
    }
    /*
    删除歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Song song = songService.selectByPrimaryKey(Integer.parseInt(id));
        String url = song.getUrl();
        String pic = song.getPic();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") ;
        String filePath1=System.getProperty("user.dir");
        File file = new File(filePath1 +url);
        File file1=new File(filePath1+pic);
        //判断此文件是否为空
        if(file!=null && file1!=null){
            //文件不为空，执行删除
            file.delete();
            file1.delete();
        }else {
            //为空提示错误信息
            request.setAttribute("error","文件不存在，不能删除");
        }
        boolean flag = songService.delete(Integer.parseInt(id));
        return flag;
    }


        /**
     * 更新歌曲图片
     */
    @RequestMapping(value = "/updateSongPic",method =RequestMethod.POST)
    public Object updateSongPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile avatorFile){
        JSONObject jsonObject=new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败");
            return jsonObject;
        }
        Song song1 = songService.selectByPrimaryKey(id);
        String pic = song1.getPic();
        //文件名=当前时间到毫米+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        String filePath1=System.getProperty("user.dir");
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdir();
        }
//        实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        File file1 = new File(filePath1 + pic);
        String s = filePath1 + pic;
//        System.out.println(s);
//        储存到数据库的文件地址
        String storeAvatorPath="/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(desk);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag=songService.update(song);
            if (flag){
                if (file1!=null) {
                    file1.delete();
                }
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
    /**
     * 更新歌曲文件
     */
    @RequestMapping(value = "/uploadSongUrl",method =RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile avatorFile,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MESSAGE, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫米+原来的文件名
        String fileName=System.currentTimeMillis()+avatorFile.getOriginalFilename();
        Song song1 = songService.selectByPrimaryKey(id);
        String url = song1.getUrl();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator");
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
//        实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + "song/"+fileName);
//        要删除的歌曲文件路径
        File file1=new File(filePath+url);
//        储存到数据库的文件地址
        String storeAvatorPath="/song/"+fileName;
        try {
            avatorFile.transferTo(desk);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag=songService.update(song);
            if (flag){
                if (file1!=null){
                    file1.delete();
                }else {
                    request.setAttribute("error","文件不存在，不能删除");
                }
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MESSAGE, "上传成功");
                jsonObject.put("avator",storeAvatorPath);
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
    /**
     * 根据歌曲id查询歌曲对象
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
//        List<Song> songs = songService.songOfSingerId(Integer.parseInt(songId));
//        System.out.println("==============song============="+songs);
        return songService.selectByPrimaryKey(Integer.parseInt(songId));

    } /**
     * 根据歌曲名字精确歌曲对象
     */
    @RequestMapping(value = "/songOfSongName",method = RequestMethod.GET)
    public Object songOfSongName(HttpServletRequest request){
        String songName = request.getParameter("songName").trim();
        return songService.songOfName(songName);

    }
    /**
     * 根据歌曲名字模糊查询歌曲对象
     */
    @RequestMapping(value = "/likeSongOfSongName",method = RequestMethod.GET)
    public Object likeSongOfSongName(HttpServletRequest request){
        String songName = request.getParameter("songName").trim();
        System.out.println(songName);
        return songService.likeSongOfName(songName);

    }
    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object allSinger(HttpServletRequest request){
        return songService.allSong();
    }
//    /**
//     * 根据歌手名字模糊查询
//     */
//    @RequestMapping(value = "/singerOfName",method =RequestMethod.GET)
//    public Object singerOfName(HttpServletRequest request){
//        String name = request.getParameter("name").trim();
//        return singerService.singerOfName("%"+name+"%");
//    }
//    /**
//     * 根据歌手性别
//     */
//    @RequestMapping(value = "/singerOfSex",method =RequestMethod.GET)
//    public Object singerOfSex(HttpServletRequest request){
//        String sex = request.getParameter("sex").trim();
//        return singerService.singerOfSex(Integer.parseInt(sex));
//    }
//


}
