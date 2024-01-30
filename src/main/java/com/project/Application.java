package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.project.mapper")
@PropertySource(value = { "classpath:application.yml" })
@ServletComponentScan
@ComponentScan("com.project")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
