package kr.pe.speech.webbiz.service;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

//서명된 JWT를 생성하고 검증하는 클래스
@Component
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;  // 1일을 밀리초로 계산한 것
    static final String PREFIX="Bearer";

    //TODO, 시연용도로는 이것으로 충분하지만, 운영환경에서는 애플리케이션 구성에서 비밀키를 읽어야 한다.
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //서명된 JWT 토큰 생성
    public String getToken( String username ){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration( new Date(System.currentTimeMillis()
                        + EXPIRATIONTIME ))
                .signWith(key)
                .compact();

        return token;
    }

    //요청 권한 부여 헤더에서 토큰을 가져와
    //토큰을 확인하고 사용자 이름을 얻음
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if( token != null ){
            String user = Jwts.parserBuilder()
                    .setSigningKey(key )
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if( user!=null)
                return user;
        }
        return null;
    }
}
