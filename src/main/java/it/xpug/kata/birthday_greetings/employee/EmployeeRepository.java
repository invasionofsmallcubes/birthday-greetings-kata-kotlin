package it.xpug.kata.birthday_greetings.employee;

import it.xpug.kata.birthday_greetings.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> load();
}
