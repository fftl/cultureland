package com.ssafy.culture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.ssafy.**.dao"})
//@ComponentScan(basePackages = {"com.ssafy"})
public class CulturelandBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CulturelandBackApplication.class, args);
	}

}
