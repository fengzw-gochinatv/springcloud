package com.example.springcloud_serice_feign.service;

import com.example.springcloud_serice_feign.po.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: fengzw
 * @Description: todo
 * @Date 2018/6/20下午4:45
 */

@Component
public class UserServiceFallback implements UserService {



    @Override
    public String test(@RequestParam(value="name") String name) {
        return "user service is not valid";
    }

    @Override
    public List<User> query() {
        return null;
    }

    @Override
    public String testzipkin(String name) {
        return null;
    }
}
