package com.fwtai.filters;

import com.fwtai.service.MyUserDetailsService;
import com.fwtai.tool.ToolJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
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
@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private ToolJwt toolJwt;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,final HttpServletResponse response,final FilterChain filterChain) throws ServletException, IOException{
        final String authorization = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if(authorization != null && authorization.startsWith("Bearer ")){
            jwt = authorization.substring(7);
            username = toolJwt.extractUsername(jwt);
        }
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        //判断是否为空再继续
        if(username != null && securityContext.getAuthentication() == null){
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(toolJwt.validateToken(jwt,userDetails)){
                final UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernameToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(usernameToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}