package com.gen.music.service;




import com.gen.music.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 歌手
 *
 */
@Repository
public interface CommentService {
    /**
     * 增加
     * @param comment
     * @return
     */
    boolean insert(Comment comment);

    /**
     * 修改
     * @param comment
     * @return
     */
    boolean update(Comment comment);

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
    Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     * @return
     */
    List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     * @param songId
     * @return
     */
    List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下所有评论
     * @param songListId
     * @return
     */
    List<Comment> commentOfSongListId(Integer songListId);

}
