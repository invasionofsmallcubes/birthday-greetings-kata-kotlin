package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;

import static javax.mail.Transport.send;

public class SenderService {

    private final MessageBuilder messageBuilder;

    public SenderService(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    void sendMessageTo(Employee e) {
        try {
            send(messageBuilder.buildMessage(e));
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
