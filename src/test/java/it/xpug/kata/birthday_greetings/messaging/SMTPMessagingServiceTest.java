package it.xpug.kata.birthday_greetings.messaging;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class SMTPMessagingServiceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private static final int PORT = NONSTANDARD_PORT;
    private static final String SMTP_HOST = "localhost";
    private MessagingService messagingService;
    private SimpleSmtpServer mailServer;

    @Before
    public void setUp() {
        mailServer = SimpleSmtpServer.start(PORT);
        messagingService = new SMTPMessagingService(SMTP_HOST, PORT);
    }

    @After
    public void tearDown() throws Exception {
            mailServer.stop();
            sleep(200);
    }

    @Test
    public void succesfullySend() {
        messagingService.sendMessage("me", "ciao", "body", "you");
        assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
        SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
        assertEquals("body", message.getBody());
        assertEquals("ciao", message.getHeaderValue("Subject"));
        String[] recipients = message.getHeaderValues("To");
        assertEquals(1, recipients.length);
        assertEquals("you", recipients[0]);
    }

    @Test(expected = CantSendMessage.class)
    public void breakIt() throws InterruptedException {
        mailServer.stop();
        sleep(200);
        messagingService.sendMessage("me", "ciao", "body", "you");
    }
}
