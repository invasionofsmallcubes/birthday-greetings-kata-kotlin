package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import it.xpug.kata.birthday_greetings.conf.Config;
import org.junit.*;

import com.dumbster.smtp.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Session;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Config.class)
@ActiveProfiles("test")
public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private SimpleSmtpServer mailServer;

	@Autowired
	private BirthdayService birthdayService;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings(new XDate("2008/10/08"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}
