package com.fuhua.wx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * RestTemplate教程： https://blog.csdn.net/ldy1016/article/details/80002126
 * https://www.jianshu.com/p/c9644755dd5e
 */
@Component
@Configuration
public class ApiConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        // 全局构建一个实例，不需要到处new一个实例出来
        RestTemplate restTemplate = new RestTemplate(factory);

        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());

        return restTemplate;
    }





    @Bean
    public ClientHttpRequestFactory factory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return factory;
    }

    /**
     * @desc 微信返回的contentType为text/plain，需要追加转换类型
     **/
    private class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        private WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
