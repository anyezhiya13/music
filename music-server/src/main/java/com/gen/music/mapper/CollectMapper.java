package com.gen.music.mapper;


import com.gen.music.domain.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论
 *
 */

@Repository
public interface CollectMapper {
    /**
     * 增加
     * @param collect
     * @return
     */
    int insert(Collect collect);


    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    public int deleteByUserIdSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);


    /**
     * 查询所有收藏
     * @return
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户下的所有收藏
     * @param userId
     * @return
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 查询某个用户是否收藏某个歌曲
     * @param songId
     * @return
     */
    int existSongId(@Param("songId") Integer songId,@Param("userId") Integer userId);
    /**
     * 查询某个用户是否收藏某个歌曲
     * @param songListId
     * @return
     */
    int existSongListId(@Param("songListId") Integer songListId,@Param("userId") Integer userId);


}
