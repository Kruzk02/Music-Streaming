package MusicStreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    
    public void sendVerificationEmail(String to,String token){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Verify Your Account");
        mailMessage.setText("To verify your account, click on the following link: http://localhost:8080/api/verify?token=" + token);
        javaMailSender.send(mailMessage);
    }
}
