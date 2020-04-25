package com.fwtai.models;

import java.io.Serializable;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 1:09
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
public final class AuthenticationRequest implements Serializable{

    private String username;
    private String password;

    public AuthenticationRequest(){
    }

    public AuthenticationRequest(final String username,final String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}