package com.zzx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.zzx.mapper")
@ComponentScan(basePackages = "com.zzx")
@EnableAutoConfiguration()
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
