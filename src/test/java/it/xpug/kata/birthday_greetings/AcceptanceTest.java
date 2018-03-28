package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.messaging.MessagingService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class AcceptanceTest {

    private BirthdayService birthdayService;
    private MessagingService messagingService = mock(MessagingService.class);

    @Before
    public void setUp() {
        birthdayService = new BirthdayService(messagingService);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/10/08"));

        verify(messagingService).sendMessage("sender@here.com", "Happy Birthday!",
                "Happy Birthday, dear John!", "john.doe@foobar.com");

    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        birthdayService.sendGreetings("employee_data.txt", new XDate("2008/01/01"));
        verifyNoMoreInteractions(messagingService);
    }
}
