package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.employee.EmployeeDao;
import it.xpug.kata.birthday_greetings.messaging.MessagingService;
import it.xpug.kata.birthday_greetings.template.HappyBirthdayTemplate;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;


public class AcceptanceTest {

    private BirthdayService birthdayService;
    private MessagingService messagingService = mock(MessagingService.class);
    private HappyBirthdayTemplate expectedHappyBirthdayTemplate;
    private EmployeeDao employeeDao = mock(EmployeeDao.class);
    private Employee employee;

    @Before
    public void setUp() throws ParseException {
        employee = new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com");
        birthdayService = new BirthdayService(messagingService, employeeDao);
        expectedHappyBirthdayTemplate = new HappyBirthdayTemplate(employee);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        when(employeeDao.load()).thenReturn(asList(employee));

        birthdayService.sendGreetings(new XDate("2008/10/08"));
        verify(messagingService).sendMessage("sender@here.com", expectedHappyBirthdayTemplate);
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        when(employeeDao.load()).thenReturn(asList(employee));

        birthdayService.sendGreetings(new XDate("2008/01/01"));
        verifyNoMoreInteractions(messagingService);
    }
}
