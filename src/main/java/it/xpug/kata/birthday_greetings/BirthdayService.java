package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

class BirthdayService {

    private FileEmployeeRepository repository;
    private SenderService senderService;

    BirthdayService(FileEmployeeRepository repository, SenderService senderService) {
        this.repository = repository;
        this.senderService = senderService;
    }

    void sendGreetings(XDate xDate) throws IOException, ParseException, MessagingException {
        List<Employee> employeeList = repository.recoverEmployees();
        employeeList.stream()
                .filter(e -> e.isBirthday(xDate))
                .forEach(e -> senderService.sendMessageTo(e));
    }

}
