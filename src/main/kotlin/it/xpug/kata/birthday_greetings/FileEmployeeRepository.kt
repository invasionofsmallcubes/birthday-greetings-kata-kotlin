package it.xpug.kata.birthday_greetings

import java.io.File

class FileEmployeeRepository(private val fileName: String) {

    fun recoverEmployees(): List<Employee> {
        return File(fileName).useLines { lines ->
            lines.drop(1).map { line ->
                val employeeData = line.split(", ")
                Employee(employeeData[1], employeeData[0], XDate(employeeData[2]), employeeData[3])
            }.toList()
        }
    }

}
