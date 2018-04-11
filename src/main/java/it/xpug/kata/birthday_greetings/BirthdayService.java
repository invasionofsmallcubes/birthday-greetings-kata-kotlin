package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.employee.EmployeeDao;
import it.xpug.kata.birthday_greetings.messaging.MessagingService;
import it.xpug.kata.birthday_greetings.template.HappyBirthdayTemplate;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class BirthdayService {

    private final MessagingService messagingService;
    private final EmployeeDao employeeDao;

    BirthdayService(MessagingService messagingService, EmployeeDao employeeDao) {
        this.messagingService = messagingService;
        this.employeeDao = employeeDao;
    }

    void sendGreetings(XDate xDate) {
        loadBy(e -> e.isBirthday(xDate))
                .forEach(e -> messagingService.sendMessage("sender@here.com",
                        new HappyBirthdayTemplate(e)));
    }

    List<Employee> loadBy(Predicate<Employee> filterFunction) {
        return employeeDao
                .load()
                .stream()
                .filter(filterFunction)
                .collect(toList());
    }

}
