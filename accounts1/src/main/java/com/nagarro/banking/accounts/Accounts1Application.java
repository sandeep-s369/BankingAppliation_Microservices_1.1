package com.nagarro.banking.accounts;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class Accounts1Application {

	public static void main(String[] args) {
		SpringApplication.run(Accounts1Application.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	     return new RestTemplate();
	}    

}
