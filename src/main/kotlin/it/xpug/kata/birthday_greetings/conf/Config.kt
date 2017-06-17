package it.xpug.kata.birthday_greetings.conf

import it.xpug.kata.birthday_greetings.BirthdayService
import it.xpug.kata.birthday_greetings.FileEmployeeRepository
import it.xpug.kata.birthday_greetings.MessageBuilder
import it.xpug.kata.birthday_greetings.SenderService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.mail.Session

@Configuration
class Config {

    @Value("\${mail.port}")
    private val port: Int = 0

    @Bean
    fun session(): Session {
        val props = java.util.Properties()
        props.put("mail.smtp.host", "localhost")
        props.put("mail.smtp.port", "" + port)
        return Session.getInstance(props, null)
    }

    @Bean
    fun messageBuilder(session: Session): MessageBuilder {
        return MessageBuilder(session)
    }

    @Bean
    fun senderService(messageBuilder: MessageBuilder): SenderService {
        return SenderService(messageBuilder)
    }

    @Bean
    fun fileEmployeeRepository(): FileEmployeeRepository {
        return FileEmployeeRepository("employee_data.txt")
    }

    @Bean
    fun birthdayService(fileEmployeeRepository: FileEmployeeRepository, senderService: SenderService): BirthdayService {
        return BirthdayService(fileEmployeeRepository, senderService)
    }

}
