package com.fuhua.wx.controller;


import com.fuhua.wx.config.WxConfig;
import com.fuhua.wx.constant.WxConstant;
import com.fuhua.wx.pojo.TextMessage;
import com.fuhua.wx.service.impl.WxServiceImpl;
import com.fuhua.wx.utli.MessageXmlUtlis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController {


    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxServiceImpl wxService;


    /**
     * 第一步、配置微信服务器的URL
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319
     * token 不存在的错误 https://blog.csdn.net/chmod_R_755/article/details/75554735
     *
     * @return
     */
    @GetMapping("/msg")
    public void msg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("========WechatController========= ");

        log.info("请求进来了...");
        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        PrintWriter out = response.getWriter();
        boolean check = wxService.wxAuthenUrl(signature, timestamp, nonce, wxConfig.getToken());
        if (check) {
            // 验证通过
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 监听微信推送过来消息
     * https://blog.csdn.net/qq_33429968/article/details/72916786
     * @param req
     * @param resp
     */
    @PostMapping("/msg")
    public void wxMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("+++++++++++++++++微信推送消息进来了++++++++++++++++++++");
        // 设置一下相应的格式
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter(); // 获取resq的数据流

        Map map = null;

        try {
            map = MessageXmlUtlis.xmlToMap(req);

            String msgType = (String) map.get("MsgType");
            String event = (String) map.get("Event");
            String eventKey = (String) map.get("EventKey");
            String toUserName = (String) map.get("ToUserName");
            String fromUserName = (String) map.get("FromUserName");

            String message = null;
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());

            if ("event".equals(msgType)) {

                if (event.equals("CLICK")) {

                    textMessage.setMsgType("text");
                    if ("search_phone_money".equals(eventKey)) {
                        textMessage.setContent("话费剩余：" + Math.random() + "\n <a href=\"http://baidu.com\">点我查看詳情</a>");
                    } else {
                        textMessage.setContent("流量剩余：" + Math.random());
                    }
                    message = MessageXmlUtlis.textMessageToXml(textMessage);
                    log.info("message = {}", message);
                    out.println(message);
                }
            }


            log.info("微信传递信息 msg = {}", map);


        } catch (Exception e) {
            log.error("微信传递信息错误= {} ", e);
        } finally {
            out.close();
        }

    }

    /**
     * 客户端点击的微信授权连接
     *
     * @param scope （snsapi_userinfo，snsapi_base）
     * @return
     */
    @GetMapping("/client_url")
    public String client_url(@RequestParam(name = "scope", defaultValue = "snsapi_base") String scope, String state) {

        String url = String.format(WxConstant.wxAuthorizeUrl, wxConfig.getAppId(), wxConfig.getRedirectUrl(), scope, state);

        return "redirect:" + url;
    }


    /**
     * 获取微信授权的code地址,通过地址获取code， 再请求接口获取access_token、openid、
     *
     * @param code
     * @param state
     */
    @GetMapping("/getOppenid")
    @ResponseBody
    public HashMap getUserInfo(String code, String state) {

        HashMap userInfo = wxService.getUserAccessToken(wxConfig.getAppId(), wxConfig.getAppSecret(), code);

        return userInfo;
    }


}
