package com.usercontrol.core.emailSender;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    /*@Value("${spring.mail.username}")
    private final String from;*/

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;


    @Override
    @Async
    public void send(String to, String email) {

        try {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

            helper.setFrom("denememail@denememail.com");
            helper.setTo(to);
            helper.setSubject("Dogrulama maili (Confirmation)");
            helper.setText(email,true);

            this.javaMailSender.send(mimeMessage);

        }catch (MessagingException e){
            LOGGER.error("Eposta gonderilirken bir hata olustu: "+e.getMessage());
            throw new IllegalStateException("Mail gonderimi hatasi.");
        }

    }
}
