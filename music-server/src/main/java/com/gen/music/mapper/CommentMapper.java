package com.gen.music.mapper;



import com.gen.music.domain.Comment;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 评论
 *
 */

@Repository
public interface CommentMapper {
    /**
     * 增加
     * @param comment
     * @return
     */
    int insert(Comment comment);

    /**
     * 修改
     * @param comment
     * @return
     */
    int update(Comment comment);

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
