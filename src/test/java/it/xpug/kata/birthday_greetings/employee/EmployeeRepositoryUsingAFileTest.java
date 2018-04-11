package it.xpug.kata.birthday_greetings.employee;

import it.xpug.kata.birthday_greetings.Employee;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmployeeRepositoryUsingAFileTest {

    @Test(expected = CantReadEmployee.class)
    public void problemWhenLoadingFromNotExistentFile() throws Exception {
        EmployeeRepository employeeRepository = new EmployeeRepositoryUsingAFile("");
        employeeRepository.load();
    }

    @Test
    public void canLoadEmployees() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryUsingAFile("employee_data.txt");
        List<Employee> employees = employeeRepository.load();
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getFirstName(), is("John"));
        assertThat(employees.get(0).getEmail(), is("john.doe@foobar.com"));
    }
}