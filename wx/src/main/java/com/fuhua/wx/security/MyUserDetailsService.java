package com.fuhua.wx.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * sql获取用户信息权限返回配置
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        // TODO 根据用户名，查找到对应的密码，与权限

        // sql查询信息 封装用户信息，并返回。参数分别是：用户名，密码，用户权限

        // 对密码进行加密，这里用了BCryptPasswordEncoder加密。一般数据库都加密了MD5加密，

        String pwd = "123456";
        String password = passwordEncoder.encode(pwd);

        boolean matches = passwordEncoder.matches(pwd, password);
        log.info(String.valueOf(matches));

        log.info("密码是：{}", password);

        // User 是 userdetails 的

        // 返回信息
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return user;
    }


}
