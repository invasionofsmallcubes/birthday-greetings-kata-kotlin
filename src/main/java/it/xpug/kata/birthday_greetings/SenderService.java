package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

class SenderService {

    private final Session session;
    private final MessageBuilder messageBuilder;

    SenderService(String smtpHost, int smtpPort, MessageBuilder messageBuilder) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        this.session = Session.getInstance(props, null);
        this.messageBuilder = messageBuilder;
    }

    void sendMessageTo(Employee e) {
        try {
            Transport.send(messageBuilder.buildMessage(e, this));
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
