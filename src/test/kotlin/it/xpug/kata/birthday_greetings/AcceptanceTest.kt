package it.xpug.kata.birthday_greetings

import org.junit.Assert.*

import it.xpug.kata.birthday_greetings.conf.Config
import org.junit.*

import com.dumbster.smtp.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

import javax.mail.Session

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Config::class))
@ActiveProfiles("test")
class AcceptanceTest {
    private val mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)

    @Autowired
    lateinit private var birthdayService: BirthdayService

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {

        birthdayService.sendGreetings(XDate("2008/10/08"))

        assertEquals("message not sent?", 1, mailServer!!.receivedEmailSize.toLong())
        val message = mailServer!!.receivedEmail.next() as SmtpMessage
        assertEquals("Happy Birthday, dear John!", message.body)
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))
        val recipients = message.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"))
        assertEquals("what? messages?", 0, mailServer!!.receivedEmailSize.toLong())
    }

    companion object {
        private val NONSTANDARD_PORT = 9999
    }
}
