package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class MessageBuilder(private val session: Session) {

    fun buildMessage(e: Employee): Message {
        val msg: Message
        msg = MimeMessage(session)
        msg.setFrom(InternetAddress("sender@here.com"))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(e.email))
        msg.setSubject("Happy Birthday!")
        msg.setText("Happy Birthday, dear %NAME%!".replace("%NAME%",
                e.firstName))
        return msg
    }

}
