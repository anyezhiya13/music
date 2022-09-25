package com.gen.music.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 */
@Repository
public interface AdminMapper {

    /**
     * 验证密码是否正确
     */
    int verifyPassword(@Param("name") String name, @Param("password") String password);

}
