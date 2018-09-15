package com.example.springcloud_eureka_client_2.controller;

import com.example.springcloud_eureka_client_2.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
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
 * @Date 2018/6/20下午5:50
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @RequestMapping("/testzipkin")
    public String testzipkin(@RequestParam String name, HttpServletRequest request) {
        LOG.log(Level.INFO, "calling trace service-user2 home method  traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId"));
        return "testzipkin traceId:"+request.getHeader("X-B3-TraceId")+",spanId:"+request.getHeader("X-B3-SpanId")+",name is:"+name;
    }


}
