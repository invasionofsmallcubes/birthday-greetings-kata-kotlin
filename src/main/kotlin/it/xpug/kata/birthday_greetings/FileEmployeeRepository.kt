package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader

class FileEmployeeRepository(private val fileName: String) {
    fun recoverEmployees(): List<Employee> {
        val employeeList = mutableListOf<Employee>()
        BufferedReader(FileReader(fileName)).use {
            for (line in it.lines().skip(1)) {
                    val employeeData = line.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val employee = Employee(employeeData[1], employeeData[0], employeeData[3], XDate(employeeData[2]))
                    employeeList.add(employee)
            }
            return employeeList
        }
    }
}
