package com.example.mailmicroservice.service;

import com.example.mailmicroservice.model.MailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String mail;
    private final JavaMailSender mailSender;

    public void sendSimpleEmail(MailEntity mailEntity) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mail);
        simpleMailMessage.setTo(mailEntity.getToAddress());
        simpleMailMessage.setSubject(mailEntity.getTopic());
        simpleMailMessage.setText(mailEntity.getMessage());
        mailSender.send(simpleMailMessage);
    }

}
