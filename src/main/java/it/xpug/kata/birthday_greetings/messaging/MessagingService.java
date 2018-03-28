package it.xpug.kata.birthday_greetings.messaging;

import javax.mail.MessagingException;

public interface MessagingService {
    void sendMessage(String sender, String subject, String body, String recipient) throws MessagingException;
}
