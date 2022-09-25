package com.gen.music.domain;


import java.util.Date;

/**
 * 前端用户
 */
public class Comment {
    //主键
    private Integer id;
    // 用户id
    private Integer userId;
    // 评论类型
    private Byte type;
    // 歌曲id
    private Integer songId;
    // 歌单id
    private Integer songListId;
    // 评论内容
    private String content;
    // 评论时间
    private Date createTime;
    // 评论点赞数
    private Integer up;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }


    public Comment(Integer id, Integer userId, Byte type, Integer songId, Integer songListId, String content, Date createTime, Integer up) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.songId = songId;
        this.songListId = songListId;
        this.content = content;
        this.createTime = createTime;
        this.up = up;
    }

}
