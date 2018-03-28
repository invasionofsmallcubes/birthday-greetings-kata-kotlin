package it.xpug.kata.birthday_greetings.messaging;

import com.dumbster.smtp.SimpleSmtpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;

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
    }

    @Test(expected = CantSendMessage.class)
    public void breakIt() throws InterruptedException {
        mailServer.stop();
        sleep(200);
        messagingService.sendMessage("me", "ciao", "body", "you");
    }
}
