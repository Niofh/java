package com.fuhua.wx.dao;

import com.fuhua.wx.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserMapperTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        try{
            User user = new User();
            user.setUserName("Oufuhua");
            user.setPassword("123");
            user.setPhone("1232");

            userMapper.insert(user);


        }catch (Exception e){
            log.info("==================================");
            log.info(e.getMessage());
        }

    }
}