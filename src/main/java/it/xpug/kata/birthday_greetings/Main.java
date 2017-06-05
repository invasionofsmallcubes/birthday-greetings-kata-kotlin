package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, MessagingException {

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "" + 25);
        Session session = Session.getInstance(props, null);

        BirthdayService service = new BirthdayService(new FileEmployeeRepository("employee_data.txt"), new SenderService("localhost", 25, new MessageBuilder(session)));
        service.sendGreetings(new XDate());
    }

}
