package com.boot.bootmybatisstudent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.controller","com.service","com.po"})
@MapperScan(basePackages = {"com.mapper"})
public class BootMybatisStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisStudentApplication.class, args);
    }

}
