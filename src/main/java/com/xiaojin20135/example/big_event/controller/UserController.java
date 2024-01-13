package com.xiaojin20135.example.big_event.controller;

import com.xiaojin20135.example.big_event.pojo.Result;
import com.xiaojin20135.example.big_event.pojo.User;
import com.xiaojin20135.example.big_event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result regidter(String username, String password) {
        //查询用户
        User user = userService.findByUserName(username);
        if (null == user) {
            //没有被占用
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
        //注册

    }
}