package com.speech.cardatabase.web;

import com.speech.cardatabase.AccountCredentials;
import com.speech.cardatabase.service.EmailService;
import com.speech.cardatabase.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value="/login", method= RequestMethod.POST )
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials ){
        UsernamePasswordAuthenticationToken creds=
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                );

        Authentication auth = authenticationManager.authenticate(creds);



        //토큰 생성
        String jwts = jwtService.getToken( auth.getName() );

        //생성된 토큰으로 응답을 생성
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts )
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}
