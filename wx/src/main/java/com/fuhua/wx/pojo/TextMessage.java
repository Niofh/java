package com.fuhua.wx.pojo;

import lombok.Data;

/**
 * 发送给微信客户端的模板
 */
@Data
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

}
