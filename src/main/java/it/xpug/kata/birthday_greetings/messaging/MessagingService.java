package it.xpug.kata.birthday_greetings.messaging;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MessagingService {
    void sendMessage(String smtpHost, int smtpPort, String sender, String subject, String body, String recipient) throws MessagingException;
}
