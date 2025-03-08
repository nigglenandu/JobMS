package com.FJobMS.companyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients(basePackages = "com.FJobMS.companyms.Company.clients")
public class CompanymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymsApplication.class, args);
	}

}
