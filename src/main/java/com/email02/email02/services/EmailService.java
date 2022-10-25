package com.email02.email02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendTo(String email, String title, String text) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo("csamuele73@gmail.com");
            mimeMessageHelper.setFrom("f4kemailt3st@gmail.com");
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText("<h1>Hello Friend</h1> <h2>I wish you have a good day!</h2> <h3>" + text + "</h3>" + "<img src='cid:mrrobot' width=600>", true);
            ClassPathResource image = new ClassPathResource("mrrobot.jpg");
            mimeMessageHelper.addInline("mrrobot", image);
            mailSender.send(mimeMessage);
        }catch(Exception e){
            e.getMessage();
        }
    }


}