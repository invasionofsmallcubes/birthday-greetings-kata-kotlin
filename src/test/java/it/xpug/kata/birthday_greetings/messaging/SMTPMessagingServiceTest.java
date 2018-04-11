package it.xpug.kata.birthday_greetings.messaging;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday_greetings.Employee;
import it.xpug.kata.birthday_greetings.template.HappyBirthdayTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class SMTPMessagingServiceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private static final int PORT = NONSTANDARD_PORT;
    private static final String SMTP_HOST = "localhost";
    private MessagingService messagingService;
    private SimpleSmtpServer mailServer;
    private HappyBirthdayTemplate happyBirthdayTemplate;

    @Before
    public void setUp() throws ParseException {
        mailServer = SimpleSmtpServer.start(PORT);
        messagingService = new SMTPMessagingService(SMTP_HOST, PORT);
        happyBirthdayTemplate = new HappyBirthdayTemplate(new Employee("Pippo", "Topolinia", "1982/10/08", "pippo@email.it"));
    }

    @After
    public void tearDown() throws Exception {
        mailServer.stop();
        sleep(200);
    }

    @Test
    public void succesfullySend() throws ParseException {

        messagingService.sendMessage("me", happyBirthdayTemplate);

        assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
        SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
        assertEquals("Happy Birthday, dear Pippo!", message.getBody());
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
        String[] recipients = message.getHeaderValues("To");
        assertEquals(1, recipients.length);
        assertEquals("pippo@email.it", recipients[0]);
    }

    @Test(expected = CantSendMessage.class)
    public void breakIt() throws InterruptedException {
        mailServer.stop();
        sleep(200);
        messagingService.sendMessage("me", happyBirthdayTemplate);
    }
}
