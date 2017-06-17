package it.xpug.kata.birthday_greetings

class BirthdayService(private val repository: FileEmployeeRepository, private val senderService: SenderService) {

    fun sendGreetings(xDate: XDate) {
        val employeeList = repository.recoverEmployees()
        employeeList
                .filter { it.isBirthday(xDate) }
                .forEach { senderService.sendMessageTo(it) }
    }

}
