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
 * @参考1 https://blog.csdn.net/fyedu/article/details/94300733
 * @参考2 https://blog.csdn.net/weixin_39220472/article/details/80873268
 * @参考3 https://blog.csdn.net/camellia919/article/details/81945806
*/
@Service
public class MyUserDetailsService implements UserDetailsService{

    /*
        SecurityContextHolder作为全局缓存，从上下文获取授权信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        ---------------------------------------------------------------------------------------
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
     */


    //在实际项目中应该是中数据库里获取数据,此处的用户名和密码是写死的
    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException{
        return new User("admin","123456",new ArrayList<>());//第3个参数是授权列表,可以在这里实现动态权限列表
    }
}