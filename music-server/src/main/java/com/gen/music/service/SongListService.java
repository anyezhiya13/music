package com.gen.music.service;


import com.gen.music.domain.Song;
import com.gen.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单service接口
 *
 */
@Repository
public interface SongListService {
    /**
     * 增加
     * @param songList
     * @return
     */
    boolean insert(SongList songList);

    /**
     * 修改
     * @param songList
     * @return
     */
    boolean update(SongList songList);

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
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有A歌单
     * @return
     */
    List<SongList> allSongList();

    /**
     * 根据标题精确查询
     * @param title
     * @return
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询
     * @param title
     * @return
     */
    List<SongList> likeTitle(String title);

    /**
     * 根据风格模糊查询
     * @param style
     * @return
     */
    List<SongList> likeStyle(String style);

}
