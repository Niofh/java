package com.fuhua.wx.service;

import com.fuhua.wx.pojo.menu.Menu;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号接口封装
 */
public interface IWxService {


    boolean wxAuthenUrl(String signature, String timestamp, String nonce, String token);

    String getWxAccessToken();


    HashMap getUserAccessToken(String appId, String secret, String code);

    HashMap getUserInfo(String accessToken, String openid, String lang);

    void createMenu(Menu menu);
}
