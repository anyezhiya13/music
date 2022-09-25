package com.gen.music.service.impl;

import com.gen.music.domain.Collect;
import com.gen.music.mapper.CollectMapper;
import com.gen.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    /**
     * 增加
     *
     * @param collect
     * @return
     */
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insert(collect)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return collectMapper.delete(id)>0;
    }

    /**
     * 根据用户id和歌曲id删除
     *
     * @param userId
     * @param songId
     */
    @Override
    public boolean deleteByUserIdSongId(Integer userId, Integer songId) {
        return collectMapper.deleteByUserIdSongId(userId,songId)>0;
    }
    /**
     * 查询所有收藏
     *
     * @return
     */
    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    /**
     * 查询某个用户下的所有收藏
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    /**
     * 查询某个用户是否收藏某个歌曲
     *
     * @param songId
     * @param userId
     * @return
     */
    @Override
    public boolean existSongId(Integer songId, Integer userId) {
        return collectMapper.existSongId(songId,userId)>0;
    }

    /**
     * 查询某个用户是否收藏某个歌曲
     *
     * @param songListId
     * @param userId
     * @return
     */
    @Override
    public boolean existSongListId(Integer songListId, Integer userId) {
        return collectMapper.existSongListId(songListId,userId)>0;
    }
}
