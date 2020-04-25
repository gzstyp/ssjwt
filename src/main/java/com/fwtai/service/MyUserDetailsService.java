package com.fwtai.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 认证
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 0:16
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Service
public class MyUserDetailsService implements UserDetailsService{

    //在实际项目中应该是中数据库里获取数据,此处的用户名和密码是写死的
    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException{
        return new User("admin","123456",new ArrayList<>());//第3个参数是授权列表
    }
}