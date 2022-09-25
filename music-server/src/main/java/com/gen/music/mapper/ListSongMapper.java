package com.gen.music.mapper;


import com.gen.music.domain.ListSong;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单里面的歌曲
 *
 */
@Repository
public interface ListSongMapper {
    /**
     * 增加
     * @param listSong
     * @return
     */
    int insert(ListSong listSong);

    /**
     * 修改
     * @param listSong
     * @return
     */
    int update(ListSong listSong);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除
     * @return
     */
    int deleteBySongIdAndBySongListId(@Param("songId") Integer songId,@Param("songListId") Integer songListId);
    /**
     * 根据主键查询整个对象
     * @param id
     * @return
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单里面的歌曲
     * @return
     */
    List<ListSong> allListSong();


    /**
     * 根据歌单id查询所有的歌曲
     * @param songListId
     * @return
     */
    List<ListSong> listSongOfSongListId(Integer songListId);

}
