package com.example.springcloud_service_ribbon.service;

import com.example.springcloud_service_ribbon.po.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengzw
 * @Description: todo
 * @Date 2018/6/20下午2:36
 */


@Service
public class HelloService {


    @Autowired
    RestTemplate restTemplate;


    /**
     * 在hiService方法上加上@HystrixCommand注解。
     * 该注解对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法，
     * 熔断方法直接返回了一个字符串，字符串为”hi,”+name+”,sorry,error!”，代码如下：
     * @param name
     * @return
     *
     */
    //@HystrixCommand(fallbackMethod = "testFallback")
    public String test(String name){


        return restTemplate.getForObject("http://service-user/user/test?name="+name,String.class);


    }

    public String testFallback(String name){

        return "service-user test error,hystrix ；"+name;
    }


    @HystrixCommand(fallbackMethod = "queryFallback")
    public List<User> query(){

        return restTemplate.getForObject("http://service-user/user/query",List.class);


    }

    public List<User> queryFallback(){

        List<User> list = new ArrayList<>();

        User user = new User();
        user.setId("0000");
        user.setName("queryFallback");
        list.add(user);

        return list;
    }


    public String testzipkin(String name){

        return restTemplate.getForObject("http://service-user/user/testzipkin?name="+name,String.class);


    }
}
