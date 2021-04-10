package com.hzl.springbootes.controller;

import com.hzl.springbootes.entity.User;
import com.hzl.springbootes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HZLin
 * @description
 * @date 2021/4/8
 */
@RestController
public class UserController {
//    @Autowired
    @Resource
    private UserService userService;

    @GetMapping("/add")
    public void add(){
        userService.add();
    }

    @GetMapping("/update")
    public void update(User user){
        userService.update(user);
    }

    @GetMapping("/get")
    public List<User> get(){
        return userService.listAll();
    }

    @GetMapping("/find")
    public List<User> find(){
        return userService.find();
    }

}
