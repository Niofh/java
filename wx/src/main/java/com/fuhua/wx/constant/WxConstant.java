package com.fuhua.wx.constant;

public interface WxConstant {

    String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";

    /*获取 accessToken Url*/
    String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /*微信授权url*/
    String wxAuthorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /*通过code换取网页授权access_token*/
    String wxAuthorizeTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /*获取用户信息*/
    String wxUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s";

    /*创建微信菜单*/
    String createWxMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";


}
