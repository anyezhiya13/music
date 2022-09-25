package com.gen.music.mapper;


import com.gen.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手
 *
 */
@Repository
public interface SongListMapper {
    /**
     * 增加
     * @param songList
     * @return
     */
    int insert(SongList songList);

    /**
     * 修改
     * @param songList
     * @return
     */
    int update(SongList songList);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    List<SongList> allSongList();

    /**
     * 根据标题精确查询歌单列表
     * @param title
     * @return
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据title模糊查询歌单列表
     */
    List<SongList> likeTitle(String title);
    /**
     * 根据风格模糊查询歌单列表
     */
    List<SongList> likeStyle(String style);
}
