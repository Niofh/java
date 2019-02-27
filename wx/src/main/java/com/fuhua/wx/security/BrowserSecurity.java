package com.fuhua.wx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class BrowserSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;


    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 可以使用postman请求接口登录  保存密码推荐用Bcrypt.
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // form-login属性详解 https://blog.csdn.net/yin380697242/article/details/51893397

        http

                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<Object>() {
                    @Override
                    public <O> O postProcess(O object) {
                        return object;
                    }
                })

                .antMatchers("/login").permitAll()  // 设置所有人都可以访问登录页面
                .antMatchers("/user/login").permitAll()  // 设置所有人都可以访问登录页面
                .anyRequest().authenticated()  // 任何请求,登录后可以访问

                .and()

                .formLogin()          // form表单登录,不能用于后端当做接口来调用
                .loginPage("/login")  // 设置登录页面
                .loginProcessingUrl("/user/login")  // 自定义的登录接口 ajax请求这个接口登录
                .successHandler(myAuthenticationSuccessHandler) // 成功处理函数
                .failureHandler(myAuthenctiationFailureHandler);


        http.csrf().disable();

//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
//
//                .and()
//
//                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/login").permitAll() // 登录是放开的
//
//                .anyRequest()               // 所有请求都要登录认证才能访问
//                .authenticated()
//
//                .and()
//                .cors().disable();
    }


}
