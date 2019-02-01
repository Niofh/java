package com.fuhua.wx.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    WX_ACCESS_TOKEN_ERR(10001, "微信获取accessToken错误"),
    CODE_ERR(10002, "通过code换取网页授权获取access_token错误"),
    WX_CREATE_MENU_ERR(10003, "微信创建菜单失败"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
