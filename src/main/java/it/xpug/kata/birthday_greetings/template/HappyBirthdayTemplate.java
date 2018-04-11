package it.xpug.kata.birthday_greetings.template;

import it.xpug.kata.birthday_greetings.Employee;

public class HappyBirthdayTemplate {
    private Employee employee;

    public HappyBirthdayTemplate(Employee employee) {
        this.employee = employee;
    }

    public String body() {
        return "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
    }

    public String recipient() {
        return employee.getEmail();
    }

    public String subject() {
        return "Happy Birthday!";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HappyBirthdayTemplate that = (HappyBirthdayTemplate) o;

        return employee.equals(that.employee);
    }

    @Override
    public int hashCode() {
        return employee.hashCode();
    }

    @Override
    public String toString() {
        return "HappyBirthdayTemplate{" +
                "employee=" + employee +
                '}';
    }
}
