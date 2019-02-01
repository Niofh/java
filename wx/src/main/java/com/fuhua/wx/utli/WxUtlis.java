package com.fuhua.wx.utli;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WxUtlis {


    /**
     * 校验服务器签名
     * <p>
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319
     *
     * @return boolean
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        log.info("signature = {},timestamp = {},nonce = {},token = {}", signature, timestamp, nonce, token);

        String[] arr = new String[]{token, timestamp, nonce};


        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }


        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = WxUtlis.byteToStr(digest);  // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        System.out.println(tmpStr.equals(signature.toUpperCase()));

        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
