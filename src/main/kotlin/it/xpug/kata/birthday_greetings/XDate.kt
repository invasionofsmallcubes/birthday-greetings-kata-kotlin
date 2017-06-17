package it.xpug.kata.birthday_greetings

import java.time.LocalDate
import java.time.LocalDate.parse
import java.time.format.DateTimeFormatter.ofPattern

data class XDate(val date: LocalDate) {

    constructor(yyyyMMdd: String) : this(parse(yyyyMMdd, formatter))

    val day
        get() = date.dayOfMonth

    val month
        get() = date.month.value

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == this.day && anotherDate.month == this.month
    }

    companion object {
        val formatter = ofPattern("yyyy/MM/dd")
    }
}
