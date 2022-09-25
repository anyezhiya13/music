package com.gen.music.mapper;


import com.gen.music.domain.Consumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前端用户
 *
 */
@Repository
public interface ConsumerMapper {
    /**
     * 增加
     * @param consumer
     * @return
     */
    int insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     * @return
     */
    int update(Consumer consumer);

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
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<Consumer> allConsumer();


    /**
     * 验证密码
     */
    int verifyPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据账号查询
     */

    Consumer getByUsername(String username);


}
