package com.azhuo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 生成jwt令牌
     */
    @Test
    public void testGenJwt() {
        // 1.封装自定义数据
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 10001);
        dataMap.put("username", "azhuo");

        // 2.生成jwt令牌
        String jwt = Jwts.builder()
                .setClaims(dataMap) // 自定义数据
                .signWith(SignatureAlgorithm.HS256, "MTIzNDU2") // 签名算法和密钥 base64编码
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 过期时间1小时
                .compact(); // 生成jwt令牌
        // 3.打印jwt令牌
        System.out.println(jwt);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    public void testParseJwt() {
        // 1.解析jwt令牌
        Claims claims = Jwts.parser()
                .setSigningKey("MTIzNDU2") // 签名密钥 base64编码
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwMDEsImV4cCI6MTc2MDEwMDYxOCwidXNlcm5hbWUiOiJhemh1byJ9.KrDYrOFpCWHHZphKTvAFWQc2f3GuBFe6ts301ac-HhY")
                .getBody(); // 解析jwt令牌
        // 2.打印claims
        System.out.println(claims);
        // 3.打印自定义数据
        System.out.println(claims.get("id"));
        System.out.println(claims.get("username"));
    }

}
