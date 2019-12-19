package com.f.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableAutoConfiguration
public class WxApplication {

	public static void main (String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}

}
