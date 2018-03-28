package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.messaging.SMTPMessagingService;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, MessagingException {
		BirthdayService service = new BirthdayService(new SMTPMessagingService("localhost", 25));
		service.sendGreetings("employee_data.txt", new XDate());
	}

}
