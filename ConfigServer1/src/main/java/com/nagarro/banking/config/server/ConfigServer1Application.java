package com.nagarro.banking.config.server;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer  // This is new adding
public class ConfigServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServer1Application.class, args);
	}

}
