package com.xiaojin20135.example.big_event.controller;

import com.xiaojin20135.example.big_event.pojo.Result;
import com.xiaojin20135.example.big_event.pojo.User;
import com.xiaojin20135.example.big_event.service.UserService;
import com.xiaojin20135.example.big_event.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result regidter(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User user = userService.findByUserName(username);
        if (null == user) {
            //没有被占用
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUserName(username);
        if (null == loginUser) {
            return Result.error("用户名不存在");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            return Result.success("jwt token");
        }
        return Result.error("密码错误");
    }
}
