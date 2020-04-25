package com.fwtai.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * json web token 工具类
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-04-26 0:37
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Service
public class ToolJwt{

    private final String SECRET_KEY = "secret";

    public String extractUsername(final String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(final String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(final String token,Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(final String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //是否有效
    private Boolean isTokenExpired(final String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(final UserDetails userDetails){
        final Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(final Map<String,Object> claims,final String subject){
        final long date = System.currentTimeMillis();
        return Jwts.builder()
          .setClaims(claims)
          .setSubject(subject)
          .setIssuedAt(new Date(date))
          .setExpiration(new Date(date + 1000 * 60 * 60 * 10))
          .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
          .compact();
    }

    public Boolean validateToken(final String token,final UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}