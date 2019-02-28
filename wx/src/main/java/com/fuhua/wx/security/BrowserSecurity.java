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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * spring security 全局配置
 */
@EnableWebSecurity
@Configuration
public class BrowserSecurity extends WebSecurityConfigurerAdapter {




    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;


    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 可以使用postman请求接口登录  保存密码推荐用Bcrypt.
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // form-login属性详解 https://blog.csdn.net/yin380697242/article/details/51893397

        http


                .rememberMe() // 记住我 登录
                .tokenRepository(this.persistentTokenRepository()) // 设置存储地方
                .rememberMeParameter("rememberMe") // name="rememberMe"
                .tokenValiditySeconds(3600) // 过期时间 3600秒
                .userDetailsService(myUserDetailsService)

                .and()
                .formLogin()          // form表单登录,不能用于后端当做接口来调用
                .loginPage("/login")  // 设置登录页面
                .loginProcessingUrl("/user/login")  // 自定义的登录接口 ajax请求这个接口登录


                .successHandler(myAuthenticationSuccessHandler) // 成功处理函数
                .failureHandler(myAuthenctiationFailureHandler)
                .and()

                .authorizeRequests()
                .antMatchers("/login").permitAll()  // 设置所有人都可以访问登录页面
                .antMatchers("/user/login").permitAll()  // 设置所有人都可以访问登录接口
                .anyRequest().authenticated();  // 任何请求,登录后可以访问




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
