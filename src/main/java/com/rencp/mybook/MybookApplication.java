package com.rencp.mybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.rencp.mybook.mapper")
public class MybookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybookApplication.class, args);
    }

}
