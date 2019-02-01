package com.fuhua.wx.service.impl;

import com.fuhua.wx.pojo.menu.Button;
import com.fuhua.wx.pojo.menu.Menu;
import com.fuhua.wx.pojo.menu.ViewButton;

import com.fuhua.wx.pojo.menu.ClickButton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxServiceImplTest {

    @Autowired
    private WxServiceImpl wxService;

    @Test
    public void getWxAccessToken() {
        String wxAccessToken = wxService.getWxAccessToken();


        System.out.println(wxAccessToken);
    }


    @Test
    public void createMenu() {


        ClickButton clickButton = new ClickButton();
        clickButton.setKey("search_phone_money");
        clickButton.setType(Button.CLICK_TYPE);
        clickButton.setName("查话费");


        ClickButton clickButton1 = new ClickButton();
        clickButton1.setKey("search_traffic");
        clickButton1.setType(Button.CLICK_TYPE);
        clickButton1.setName("查流量");


        Button button = new Button();
        ArrayList<Button> oneButton = new ArrayList<>();
        oneButton.add(clickButton);
        oneButton.add(clickButton1);
        button.setSub_button(oneButton);
        button.setName("查询");


        ViewButton towButton = new ViewButton();
        towButton.setUrl("http://fuhua.site");
        towButton.setType(Button.VIEW_TYPE);
        towButton.setName("商城");


        ViewButton threeButton = new ViewButton();
        threeButton.setUrl("http://baidu.com");
        threeButton.setType(Button.VIEW_TYPE);
        threeButton.setName("百度");


        ArrayList<Button> menuButtons = new ArrayList<>();
        menuButtons.add(button);
        menuButtons.add(towButton);
        menuButtons.add(threeButton);
        Menu menu = new Menu();
        menu.setButton(menuButtons);




          wxService.createMenu(menu);

    }
}