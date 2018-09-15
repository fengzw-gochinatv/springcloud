package com.example.springcloud_eureka_client_copy.controller;

import com.example.springcloud_eureka_client_copy.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: fengzw
 * @Description: todo
 * @Date 2018/6/26下午1:51
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @Value("${server.port}")
    private String id;


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/test")
    public User test(HttpServletRequest request,@RequestParam String name) {
        LOG.log(Level.INFO, "traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId")+",name:"+name);

        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }


    @RequestMapping("/query")
    public List<User> query(HttpServletRequest request){
        LOG.log(Level.INFO, "traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId"));

        List<User> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User();
            user.setId("id"+i);
            user.setName("testname"+i);
            list.add(user);
        }
        return list;
    }


    @RequestMapping("/testzipkin")
    public String testzipkin(@RequestParam String name, HttpServletRequest request) {

        LOG.log(Level.INFO, "calling trace service-user home method  traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId")+",name:"+name);

        String result =  restTemplate.getForObject("http://service-user2/user/testzipkin?name="+name,String.class);

        return result;
    }


    @RequestMapping("/testConfig")
    public String testConfig() {
        return "this.config";
    }

}
