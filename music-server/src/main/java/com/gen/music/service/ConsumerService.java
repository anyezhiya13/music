package com.gen.music.service;

import com.gen.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {
    /**
     * 增加
     * @param consumer
     * @return
     */
    boolean insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     * @return
     */
    boolean update(Consumer consumer);

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
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<Consumer> allConsumer();


    /**
     * 验证密码
     */
    boolean verifyPassword(String username,String password);

    /**
     * 根据账号查询
     */

    Consumer getByUsername(String username);


}
