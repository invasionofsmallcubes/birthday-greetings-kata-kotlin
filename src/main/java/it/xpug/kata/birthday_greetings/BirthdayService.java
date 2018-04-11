package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.employee.EmployeeRepository;
import it.xpug.kata.birthday_greetings.employee.EmployeeRepositoryUsingAFile;
import it.xpug.kata.birthday_greetings.messaging.MessagingService;
import it.xpug.kata.birthday_greetings.template.HappyBirthdayTemplate;

public class BirthdayService {

    private final MessagingService messagingService;
    private EmployeeRepository employeeRepository;

    BirthdayService(MessagingService messagingService, EmployeeRepository employeeRepository) {
        this.messagingService = messagingService;
        this.employeeRepository = employeeRepository;
    }

    public void sendGreetings(XDate xDate) {
        employeeRepository
                .load()
                .stream()
                .filter(e -> e.isBirthday(xDate))
                .forEach(e -> messagingService.sendMessage("sender@here.com",
                        new HappyBirthdayTemplate(e)));
    }

}
