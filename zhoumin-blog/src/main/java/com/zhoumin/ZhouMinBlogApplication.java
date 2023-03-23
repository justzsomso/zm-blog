package com.zhoumin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.zhoumin.mapper")
@EnableScheduling
public class ZhouMinBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhouMinBlogApplication.class,args);
    }
}
