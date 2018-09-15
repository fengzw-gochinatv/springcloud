package com.example.springcloud_serice_feign.service;

import com.example.springcloud_serice_feign.po.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务。
 * 比如在代码中调用了service-user服务的“/hi”接口
 */
@FeignClient(value="service-user",fallback = UserServiceFallback.class)
public interface UserService {

    @RequestMapping(value="/user/test",method = RequestMethod.GET)
     public String test(@RequestParam(value="name") String name);



    @RequestMapping(value="/user/query",method = RequestMethod.GET)
    public List<User> query();


    @RequestMapping(value="/user/testzipkin",method = RequestMethod.GET)
    public String testzipkin(@RequestParam(value="name") String name);

}
