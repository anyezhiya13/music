package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.ListSong;
import com.gen.music.service.ListSongService;
import com.gen.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 歌单歌曲管理
 */
@RestController
@RequestMapping("/listSong")
@CrossOrigin(origins = "http://localhost:8945/")
public class ListSongController {


    @Autowired
    ListSongService listSongService;


    /**
     * 给歌单添加歌曲
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //获取前端传来的参数
        String songId = request.getParameter("songId").trim();  //所属歌曲id
        String songListId = request.getParameter("songListId").trim();          //歌单id
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.insert(listSong);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "保存失败");
        return jsonObject;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object detail(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        return listSongService.listSongOfSongListId(Integer.parseInt(songListId));

    }

    /*
    删除歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        boolean flag = listSongService.deleteBySongIdAndBySongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        return flag;
    }

}
