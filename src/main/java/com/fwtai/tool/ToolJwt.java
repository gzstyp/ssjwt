package com.fwtai.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
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

    //注意这个claims是没有值的哦
    public String generateToken(final UserDetails userDetails){
        final Map<String,Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities());
        return createToken(userDetails.getUsername(),claims);
    }

    // setSubject 不能和s etClaims() 同时使用,如果用不到 userId() 的话可以把setId的值设为 userName !!!
    private String createToken(final String subject,final Map<String,Object> claims){
        final long date = System.currentTimeMillis();
        final JwtBuilder builder = Jwts.builder();
        if(claims != null && claims.size() >0){
            for(final String key : claims.keySet()){
                builder.claim(key,claims.get(key));
            }
        }
        return builder.setSubject(subject)
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