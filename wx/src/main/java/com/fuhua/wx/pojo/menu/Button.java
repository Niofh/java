package com.fuhua.wx.pojo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;


/**
 * 创建菜单基础类型
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Button {

    public static final String CLICK_TYPE = "click";
    public static final String VIEW_TYPE = "view";

    private String type;

    private String name;

    private List<Button> sub_button;

}
