package com.gen.music.mapper;


import com.gen.music.domain.Singer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手
 *
 */
@Repository
public interface SingerMapper {
    /**
     * 增加
     * @param singer
     * @return
     */
    int insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    int update(Singer singer);

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
