package it.xpug.kata.birthday_greetings

import javax.mail.Transport.send

class SenderService(private val messageBuilder: MessageBuilder) {

    fun sendMessageTo(e: Employee) {
        send(messageBuilder.buildMessage(e))
    }

}
