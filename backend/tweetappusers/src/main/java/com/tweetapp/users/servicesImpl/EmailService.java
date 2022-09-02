package com.tweetapp.users.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    void sendEmail(String email, int otp) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(email);
//
//        msg.setSubject("Reset Password OTP from Tweet App");
//        msg.setText("OTP : "+ otp);
//
//        javaMailSender.send(msg);
//
//    }
 void sendmail(String email, int otp) throws AddressException, MessagingException, IOException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("brain2ivor@gmail.com", "ojidueavswkkvfcu");
        }
    });
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("tweetapp@gmail.com"));
     message.setRecipients(
             Message.RecipientType.TO,
             InternetAddress.parse(email)
     );
     message.setSubject("OTP for Tweet App Password reset");
     message.setText("Dear User,"
             + "\n\n OTP : "+otp);
    Transport.send(message);
}
}
