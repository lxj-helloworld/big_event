package com.xiaojin20135.example.big_event.service.impl;

import com.xiaojin20135.example.big_event.mapper.UserMapper;
import com.xiaojin20135.example.big_event.pojo.User;
import com.xiaojin20135.example.big_event.service.UserService;
import com.xiaojin20135.example.big_event.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //密码加密
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }
}
