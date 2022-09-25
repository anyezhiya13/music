package com.gen.music.service.impl;

import com.gen.music.mapper.AdminMapper;
import com.gen.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    /**
     * 验证密码是否正确
     *
     * @param name
     * @param password
     */
    @Override
    public boolean verifyPassword(String name, String password) {
        int i = adminMapper.verifyPassword(name, password);
        System.out.println("==========="+i);
        return adminMapper.verifyPassword(name, password)>0?true:false;
    }
}
