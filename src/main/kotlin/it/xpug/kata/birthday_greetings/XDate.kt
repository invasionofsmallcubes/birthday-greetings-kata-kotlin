package it.xpug.kata.birthday_greetings

import java.text.SimpleDateFormat
import java.util.*

data class XDate(val date: Date) {

    constructor(yyyyMMdd: String) : this(SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd))

    val day
        get() = getPartOfDate(GregorianCalendar.DAY_OF_MONTH)

    val month
        get() = 1 + getPartOfDate(GregorianCalendar.MONTH)

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == this.day && anotherDate.month == this.month
    }

    private fun getPartOfDate(part: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = date
        return calendar.get(part)
    }
}
