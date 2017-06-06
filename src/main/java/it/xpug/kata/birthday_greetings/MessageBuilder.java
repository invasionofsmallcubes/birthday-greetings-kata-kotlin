package it.xpug.kata.birthday_greetings;

import org.jetbrains.annotations.NotNull;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageBuilder {

    private final Session session;

    public MessageBuilder(Session session) {
        this.session = session;
    }

    @NotNull
    Message buildMessage(Employee e) throws MessagingException {
        Message msg;
        msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("sender@here.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(e.getEmail()));
        msg.setSubject("Happy Birthday!");
        msg.setText("Happy Birthday, dear %NAME%!".replace("%NAME%",
                e.getFirstName()));
        return msg;
    }
}
