package it.xpug.kata.birthday_greetings;

import java.util.List;

public class BirthdayService {

    private FileEmployeeRepository repository;
    private SenderService senderService;

    public BirthdayService(FileEmployeeRepository repository, SenderService senderService) {
        this.repository = repository;
        this.senderService = senderService;
    }

    void sendGreetings(XDate xDate) {
        List<Employee> employeeList = repository.recoverEmployees();
        employeeList.stream()
                .filter(e -> e.isBirthday(xDate))
                .forEach(e -> senderService.sendMessageTo(e));
    }

}
