package com.example.springcloud_service_ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class SpringcloudServiceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServiceRibbonApplication.class, args);
	}


	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){

		return new RestTemplate();
	}
}
