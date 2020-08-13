package com.infy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@PropertySource("classpath:message.properties")
@SpringBootApplication
@EnableWebMvc
public class UserRegisterationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegisterationApplication.class, args);
	}

}
