package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.employee.EmployeeRepositoryUsingAFile;
import it.xpug.kata.birthday_greetings.messaging.SMTPMessagingService;

public class Main {

    public static void main(String[] args) {

        BirthdayService service = new BirthdayService(new SMTPMessagingService("localhost", 25),
                new EmployeeRepositoryUsingAFile("employee_data.txt"));

        service.sendGreetings(new XDate());
    }

}
