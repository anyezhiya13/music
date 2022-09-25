package com.gen.music.service.impl;

import com.gen.music.domain.ListSong;
import com.gen.music.mapper.ListSongMapper;
import com.gen.music.service.ListSongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {


    @Resource
    ListSongMapper listSongMapper;
    /**
     * 增加
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong)>0;
    }

    /**
     * 修改
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id)>0;
    }

    /**
     * 根据歌曲id和歌单id删除
     *
     * @param songId
     * @param songListId
     * @return
     */
    @Override
    public boolean deleteBySongIdAndBySongListId(Integer songId, Integer songListId) {
        return listSongMapper.deleteBySongIdAndBySongListId(songId,songListId)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌单里面的歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据歌单id查询所有的歌曲
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }
}
