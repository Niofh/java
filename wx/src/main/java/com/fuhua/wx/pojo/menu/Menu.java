package com.fuhua.wx.pojo.menu;

import lombok.Data;

import java.util.List;


/**
 * 创建自定义菜单的集合
 */
@Data
public class Menu {
    private List<Button> button;
}
