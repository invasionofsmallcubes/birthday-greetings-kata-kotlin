package it.xpug.kata.birthday_greetings.employee;

import it.xpug.kata.birthday_greetings.Employee;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmployeeDaoUsingAFileTest {

    @Test(expected = CantReadEmployee.class)
    public void problemWhenLoadingFromNotExistentFile() throws Exception {
        EmployeeDao employeeDao = new EmployeeDaoUsingAFile("");
        employeeDao.load();
    }

    @Test
    public void canLoadEmployees() {
        EmployeeDao employeeDao = new EmployeeDaoUsingAFile("employee_data.txt");
        List<Employee> employees = employeeDao.load();
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getFirstName(), is("John"));
        assertThat(employees.get(0).getEmail(), is("john.doe@foobar.com"));
    }
}