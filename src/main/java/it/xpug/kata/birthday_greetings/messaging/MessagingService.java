package it.xpug.kata.birthday_greetings.messaging;

import it.xpug.kata.birthday_greetings.template.HappyBirthdayTemplate;

public interface MessagingService {
    void sendMessage(String me, HappyBirthdayTemplate happyBirthdayTemplate);
}
