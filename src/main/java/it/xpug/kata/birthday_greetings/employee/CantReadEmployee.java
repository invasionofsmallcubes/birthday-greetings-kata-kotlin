package it.xpug.kata.birthday_greetings.employee;

public class CantReadEmployee extends RuntimeException {
    public CantReadEmployee(Exception e) {
        super(e);
    }
}
