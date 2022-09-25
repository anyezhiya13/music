package com.gen.music.mapper;


import com.gen.music.domain.Singer;
import com.gen.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手
 *
 */
@Repository
public interface SongMapper {
    /**
     * 增加
     * @param song
     * @return
     */
    int insert(Song song);

    /**
     * 修改
     * @param song
     * @return
     */
    int update(Song song);

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
    Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    List<Song> allSong();

    /**
     * 根据歌名精确查询
     * @param name
     * @return
     */
    List<Song> songOfName(String name);

    /**
     * 根据歌手id查询
     * @param singerId
     * @return
     */
    List<Song> songOfSingerId(Integer singerId);
    /**
     * 根据歌名精确查询
     * @param name
     * @return
     */
    List<Song> likeSongOfName(String name);

}
