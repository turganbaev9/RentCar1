package kg.mega.emailservice.service;

public interface MailSenderService {

   void sendSimpleMessage(
            String to, String subject, String text);
}
