package com.example.springcloud_service_ribbon.controller;

import com.example.springcloud_service_ribbon.po.User;
import com.example.springcloud_service_ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: fengzw
 * @Description: todo
 * @Date 2018/6/20下午2:38
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());


    @Autowired
    HelloService helloService;

    @RequestMapping("/test")
    public String test(@RequestParam String name,HttpServletRequest request) {
        LOG.log(Level.INFO, " traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId"));

        return helloService.test(name);
    }


    @RequestMapping("/query")
    public List<User> query(HttpServletRequest request) {
        return helloService.query();
    }



    @RequestMapping("/testzipkin")
    public String testzipkin(@RequestParam String name, HttpServletRequest request) {

        return helloService.testzipkin(name);
    }


}
