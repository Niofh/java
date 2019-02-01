package com.fuhua.wx.exception;

import com.fuhua.wx.enums.ResultEnum;

/**
 * 微信异常
 */
public class WxException extends RuntimeException {
    private Integer code;

    public WxException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public WxException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
