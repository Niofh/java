package com.fuhua.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.fuhua.wx.dao"})
public class WxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class, args);

    }

}

