package com.fuhua.wx.controller;

import com.fuhua.wx.config.WxConfig;
import com.fuhua.wx.utli.RedisUtlis;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WxControllerTest {
    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private RedisUtlis redisUtlis;


    @Test
    public void client_url() {
        redisUtlis.set("123123", "34rtvv");
    }
}