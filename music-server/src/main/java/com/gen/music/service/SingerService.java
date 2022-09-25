package com.gen.music.service;


import com.gen.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手
 *
 */
@Repository
public interface SingerService {
    /**
     * 增加
     * @param singer
     * @return
     */
    boolean insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    boolean update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     * @return
     */
    List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询
     * @param name
     * @return
     */
    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);

}
