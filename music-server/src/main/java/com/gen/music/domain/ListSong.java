package com.gen.music.domain;

import java.io.Serializable;

/**
 * 歌单里面的歌曲
 */
public class ListSong implements Serializable {
    //主键
    private Integer id;
    private Integer songId;  // 歌曲Id
    private Integer songListId;  //歌单Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }
}