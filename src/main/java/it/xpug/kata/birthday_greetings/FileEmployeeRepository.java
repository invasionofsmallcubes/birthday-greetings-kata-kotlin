package it.xpug.kata.birthday_greetings;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository {
    private String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @NotNull
    List<Employee> recoverEmployees() throws IOException, ParseException {
        List<Employee> employeeList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;
        in.readLine();
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
            employeeList.add(employee);
        }
        return employeeList;
    }
}
