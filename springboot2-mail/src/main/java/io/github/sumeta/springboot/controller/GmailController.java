package io.github.sumeta.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gmail")
public class GmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping
    public String index(){
        sentMail();
        return "gmail";
    }

    private void sentMail()	{
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo("xxx@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }
}
