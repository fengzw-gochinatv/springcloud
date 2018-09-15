package com.example.springcloud_eureka_client_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@RestController
@SpringBootApplication
public class SpringcloudEurekaClientApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringcloudEurekaClientApplication.class, args);
	}


	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){

		return new RestTemplate();
	}

}
