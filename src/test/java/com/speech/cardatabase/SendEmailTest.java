package com.speech.cardatabase;


import com.speech.cardatabase.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendEmailTest {

    @Autowired
    private EmailService emailService;


    @Test
    public void testSendMail () {

            String recipient = "sahngwoon.lee@llsollu.com";
            String subject = "Hello, KKKKKKK!";
            String template = "Hello, firstName!\n\n"
                    + "This is a message just for you, firstName lastName. "
                    + "We hope you're having a great day!\n\n"
                    + "Best regards,\n"
                    + "The Spring Boot Team";



            emailService.sendMail(recipient, subject, template);

            recipient = "makadawara@gmail.com";
            emailService.sendMail(recipient, subject, template);

            recipient = "lswbhm88@daum.net";
            emailService.sendMail(recipient, subject, template);

            recipient = "lswbhm88@naver.com";
            emailService.sendMail(recipient, subject, template);





            return;
    }
}