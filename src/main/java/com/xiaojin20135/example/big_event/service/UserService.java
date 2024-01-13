package com.xiaojin20135.example.big_event.service;

import com.xiaojin20135.example.big_event.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
