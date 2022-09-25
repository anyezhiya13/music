package com.gen.music.service;




import com.gen.music.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 歌手
 *
 */
@Repository
public interface CollectService {
    /**
     * 增加
     *
     * @param collect
     * @return
     */
    boolean insert(Collect collect);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    public boolean deleteByUserIdSongId(Integer userId, Integer songId);

    /**
     * 查询所有收藏
     *
     * @return
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户下的所有收藏
     *
     * @param userId
     * @return
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否收藏某个歌曲
     *
     * @param songId
     * @return
     */
    boolean existSongId(@Param("songId") Integer songId, @Param("userId") Integer userId);

    /**
     * 查询某个用户是否收藏某个歌曲
     *
     * @param songListId
     * @return
     */
    boolean existSongListId(@Param("songListId") Integer songListId, @Param("userId") Integer userId);
}

