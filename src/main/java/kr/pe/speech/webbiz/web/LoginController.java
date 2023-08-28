package kr.pe.speech.webbiz.web;

import kr.pe.speech.webbiz.AccountCredentials;
import kr.pe.speech.webbiz.domain.User;
import kr.pe.speech.webbiz.domain.UserRepository;
import kr.pe.speech.webbiz.service.EmailService;
import kr.pe.speech.webbiz.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds =
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                );

        Authentication auth = authenticationManager.authenticate(creds);


        //토큰 생성
        String jwts = jwtService.getToken(auth.getName());

        //생성된 토큰으로 응답을 생성
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> singIn(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds =
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                );

        System.out.println( credentials.getUsername() );
        // Welcome E-mail Message
        String recipient = "sahngwoon.lee@llsollu.com";
        String subject = "Hello, " + credentials.getUsername() ;
        String template = "Hello! \n\n"
                + "This is a message just for you.\n\n"
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "The LLsoLLu ezWEB Team";

        emailService.sendMail(recipient, subject, template);


        // save..
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword =  passwordEncoder.encode( credentials.getPassword() );
        userRepository.save( new User( credentials.getUsername(), encodedPassword , "USER" ));

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer" )
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();

    }
}
