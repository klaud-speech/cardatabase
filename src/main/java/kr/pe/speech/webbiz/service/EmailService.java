package kr.pe.speech.webbiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("sahngwoon.lee@llsollu.com");
        message.setTo( to );
        message.setSubject( subject );
        message.setText(body);


        mailSender.send(message);
    }

}
