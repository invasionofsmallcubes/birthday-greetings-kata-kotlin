package it.xpug.kata.birthday_greetings

import io.kotlintest.matchers.shouldBe


import io.kotlintest.specs.StringSpec

class EmployeeTest : StringSpec({
    "employee has a birthday" {
        val employee = Employee("foo", "bar", XDate("1990/01/31"), "a@b.c")
        employee.isBirthday(XDate("2008/01/30")) shouldBe false
        employee.isBirthday(XDate("2008/01/31")) shouldBe true
    }
    "employee equality" {
        val base = Employee("First", "Last", XDate("1999/09/01"), "first@last.com")
        val same = Employee("First", "Last", XDate("1999/09/01"), "first@last.com")
        val different = Employee("First", "Last", XDate("1999/09/01"), "boom@boom.com")

        (base == same) shouldBe true
        (base === same) shouldBe false
        (base == different) shouldBe false
    }
})