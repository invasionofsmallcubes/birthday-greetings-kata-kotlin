package it.xpug.kata.birthday_greetings.messaging;

public interface MessagingService {
    void sendMessage(String sender, String subject, String body, String recipient);
}
