package proj2.backend.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import proj2.backend.model.User;


@Service
public class EmailService {



    
    
  public void send(User user, String subject, String body) throws MessagingException {
	  final String username = "simonz.swt3@gmail.com";
      final String password = "messisudeep10";
    
	  Properties props = new Properties();
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
        });

      try {

          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("sudeep"));
          message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(user.getEmail()));
          message.setSubject(subject);
          message.setText(body);

          Transport.send(message);

          System.out.println("Done");

      } catch (MessagingException e) {
          throw new RuntimeException(e);
      }
  }
}

