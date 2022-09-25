package com.gen.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.gen.music.domain.Collect;
import com.gen.music.service.CollectService;
import com.gen.music.utils.Consts;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
@CrossOrigin(origins = "http://localhost:8945/")
public class CollectController {


    @Resource
    CollectService collectService;

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId"); // 用户id
        String type = request.getParameter("type"); // 收藏类型
        String songId = request.getParameter("songId"); // 歌曲id
        String songListId = request.getParameter("songListId"); //歌单id
        if(songId==null||songId.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MESSAGE,"收藏歌曲为空");
            return jsonObject;
        }
        if(!collectService.existSongId(Integer.parseInt(userId),Integer.parseInt(songId))){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MESSAGE,"已收藏");
            return jsonObject;
        }

        //保存到收藏对象中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(songId));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(songListId));
        }
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        boolean flag = collectService.insert(collect);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MESSAGE, "收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MESSAGE, "收藏失败");
        return jsonObject;
    }
    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        System.out.println(songId);
        boolean flag = collectService.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        System.out.println(flag);
        return flag;
    }

    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(HttpServletRequest request){
        System.out.println(collectService.allCollect());
        return collectService.allCollect();
    }
    /**
     * 查询某个用户下的所有收藏
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfSongId(HttpServletRequest request){
        String songId = request.getParameter("userId");          //歌曲id
        return collectService.collectOfUserId(Integer.parseInt(songId));
    }

}

