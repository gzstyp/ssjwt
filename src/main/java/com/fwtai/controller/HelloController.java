package com.fwtai.controller;

import com.fwtai.models.AuthenticationRequest;
import com.fwtai.models.AuthenticationResponse;
import com.fwtai.service.MyUserDetailsService;
import com.fwtai.tool.ToolJwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取token
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 0:04
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@RestController
@RequestMapping
public class HelloController{

    @Resource
    private AuthenticationManager manager;

    @Resource
    private MyUserDetailsService userDetailsService;

    @Resource
    private ToolJwt toolJwt;

    // http://127.0.0.1:8020/hello
    @GetMapping("/hello")
    public String hello(final HttpServletResponse response){
        return "hello world";
    }

    //若提供的用户名和密码都正确的话,则可以访问本接口,否则或报错'账号或密码错误'
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(final @RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        } catch (final BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("账号或密码错误");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = toolJwt.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

























}