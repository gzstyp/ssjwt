package com.fwtai.models;

import java.io.Serializable;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 1:12
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
public final class AuthenticationResponse  implements Serializable{

    private final String jwt;

    public AuthenticationResponse(final String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }
}