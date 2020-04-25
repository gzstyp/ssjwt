package com.fwtai.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截过滤器,判断请求的请求头或url参数是否含有token信息,它要做的就是检查jwt中的传入请求
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 2:58
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
public class JwtRequestFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(final HttpServletRequest request,final HttpServletResponse response,final FilterChain filterChain) throws ServletException, IOException{
    }
}