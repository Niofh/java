package com.fuhua.wx.service.impl;

import com.fuhua.wx.config.WxConfig;
import com.fuhua.wx.constant.WxConstant;
import com.fuhua.wx.enums.ResultEnum;
import com.fuhua.wx.exception.WxException;
import com.fuhua.wx.pojo.menu.Menu;
import com.fuhua.wx.service.IWxService;
import com.fuhua.wx.utli.JsonUtil;
import com.fuhua.wx.utli.RedisUtlis;
import com.fuhua.wx.utli.WxUtlis;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WxServiceImpl implements IWxService {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtlis redisUtlis;


    /**
     * 配置微信服务器的URL，true认证成功
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     */
    @Override
    public boolean wxAuthenUrl(String signature, String timestamp, String nonce, String token) {
        return WxUtlis.checkSignature(signature, timestamp, nonce, wxConfig.getToken());
    }

    /**
     * 获取微信的AccessToken
     *
     * @return
     */
    @Override
    public String getWxAccessToken() {
        // 判断redis是否存在WX_ACCESS_TOKEN
        boolean haskeyToken = redisUtlis.haskey(WxConstant.WX_ACCESS_TOKEN);

        log.info("AccessToken是否存在缓存中 = {}", haskeyToken);

        // 存在直接返回，不存在重新获取
        if (haskeyToken) {
            return (String) redisUtlis.get(WxConstant.WX_ACCESS_TOKEN);
        }
        // 获取地址
        String url = String.format(WxConstant.accessTokenUrl, wxConfig.getAppId(), wxConfig.getAppSecret());

        // 请求地址，返回数据
        HashMap map = restTemplate.getForObject(url, HashMap.class);

        String access_token = (String) map.get("access_token");

        if (StringUtils.isNotEmpty(access_token)) {
            // 获取token存入redis
            redisUtlis.set(WxConstant.WX_ACCESS_TOKEN, access_token, 7000);
            return access_token;
        }
        log.error("获取微信accessToken错误 = {}", map);
        throw new WxException(ResultEnum.WX_ACCESS_TOKEN_ERR);
    }

    /**
     * 通过code换取网页授权access_token
     *
     * @param appId
     * @param secret
     * @param code
     * @return
     */
    @Override
    public HashMap getUserAccessToken(String appId, String secret, String code) {
        String url = String.format(WxConstant.wxAuthorizeTokenUrl, appId, secret, code);
        HashMap map = restTemplate.getForObject(url, HashMap.class);
        HashMap userInfo = new HashMap();

        Integer errcode = (Integer) map.get("errcode");
        if (errcode != null && errcode != 0) {
            log.error("通过code换取网页授权 map = {}", map);
            throw new WxException(ResultEnum.CODE_ERR);
        }

        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");
        String scope = (String) map.get("scope");
        userInfo = map;

        if (scope.equals("snsapi_userinfo")) {
            userInfo = this.getUserInfo(accessToken, openid, "zh_CN");
        }

        return userInfo;
    }

    /**
     * 拉取用户信息
     *
     * @param accessToken
     * @param openid
     * @param lang
     * @return
     */
    @Override
    public HashMap getUserInfo(String accessToken, String openid, String lang) {

        String url = String.format(WxConstant.wxUserInfoUrl, accessToken, openid, lang);
        HashMap map = restTemplate.getForObject(url, HashMap.class);
        Integer errcode = (Integer) map.get("errcode");

        if (errcode != null && errcode != 0) {
            log.error("拉取用户信息错误 map = {}", map);
            throw new WxException(ResultEnum.CODE_ERR);
        }

        return map;
    }

    /**
     * 创建微信菜单
     *
     * @param menu json对象
     */
    @Override
    public void createMenu(Menu menu) {
        String wxAccessToken = this.getWxAccessToken();

        String menuUrl = String.format(WxConstant.createWxMenuUrl, wxAccessToken);

        // 传递参数
        Map<String, Object> params = new HashMap<>();
        params.put("button", menu.getButton());

        log.info("微信菜单参数paramMap = {}",  params);

        HashMap result = restTemplate.postForObject(menuUrl, params, HashMap.class);

        Integer errcode = (Integer) result.get("errcode");
        if (errcode == 0) {
            log.info("微信菜单创建成功");
        } else {
            log.error("微信菜单创建失败 = {}", result);
            throw new WxException(ResultEnum.WX_CREATE_MENU_ERR);
        }
    }
}
