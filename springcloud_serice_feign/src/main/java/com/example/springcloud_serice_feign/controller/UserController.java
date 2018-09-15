package com.example.springcloud_serice_feign.controller;

import com.example.springcloud_serice_feign.service.UserService;
import com.example.springcloud_serice_feign.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/test")
    public String test(@RequestParam String name){
        long start = System.currentTimeMillis();

        String result = userService.test(name);

        long end = System.currentTimeMillis();
        return result+" time:"+(end-start);
    }


    @RequestMapping("/query")
    public List<User> query(){
        long start = System.currentTimeMillis();
        List<User> userList =   userService.query();
        long end = System.currentTimeMillis();
        System.out.println("===query.time is :"+(end-start));
        return userList;
    }

    @RequestMapping("/testzipkin")
    public String testzipkin(@RequestParam String name){
        return userService.testzipkin(name);

    }


}
