package kg.mega.emailservice.service;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service

public class MailSenderServiceImpl implements MailSenderService {
    public MailSenderServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private  final JavaMailSender emailSender;
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
try {


    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setFrom("noreply@baeldung.com");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text);

    FileSystemResource file
            = new FileSystemResource(new File("D:\\image\\carsImages\\toyotaPrius.jpg"));
    helper.addAttachment("Invoice.jpg", file);

    emailSender.send(message);
}catch (Exception e){
    e.printStackTrace();
}
    }
}
