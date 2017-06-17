package it.xpug.kata.birthday_greetings

data class Employee(val firstName: String, private val lastName: String, val email: String, val birthDate: XDate) {
    fun isBirthday(today: XDate): Boolean {
        return today.isSameDay(birthDate)
    }
}
