package com.gen.music.service.impl;


import com.gen.music.domain.Song;
import com.gen.music.mapper.SongMapper;
import com.gen.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;


    /**
     * 增加
     *
     * @param song
     * @return
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song)>0;
    }

    /**
     * 修改
     *
     * @param song
     * @return
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id)>0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌手
     *
     * @return
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 根据歌手名字模糊查询
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }

    /**
     * 根据歌名精确查询
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> likeSongOfName(String name) {
        return songMapper.likeSongOfName('%'+name+'%');
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     * @return
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.songOfSingerId(singerId) ;
    }
}
