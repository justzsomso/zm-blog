package com.zhoumin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhoumin.mapper")
public class ZhouMinBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhouMinBlogApplication.class,args);
    }
}
