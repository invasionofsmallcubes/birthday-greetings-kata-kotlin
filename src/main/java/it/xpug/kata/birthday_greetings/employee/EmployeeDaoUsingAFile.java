package it.xpug.kata.birthday_greetings.employee;

import it.xpug.kata.birthday_greetings.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoUsingAFile implements EmployeeDao {
    private String fileName;

    public EmployeeDaoUsingAFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            List<Employee> list = new ArrayList<>();
            in.readLine();
            String str;
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                list.add(new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]));
            }
            return list;
        } catch (IOException | ParseException e) {
            throw new CantReadEmployee(e);
        }
    }
}
