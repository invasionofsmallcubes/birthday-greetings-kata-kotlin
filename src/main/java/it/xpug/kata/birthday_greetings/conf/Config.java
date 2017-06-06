package it.xpug.kata.birthday_greetings.conf;

import it.xpug.kata.birthday_greetings.BirthdayService;
import it.xpug.kata.birthday_greetings.FileEmployeeRepository;
import it.xpug.kata.birthday_greetings.MessageBuilder;
import it.xpug.kata.birthday_greetings.SenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Session;

@Configuration
public class Config {

    @Value("${mail.port}")
    private int port;

    @Bean
    public Session session() {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "" + port);
        return Session.getInstance(props, null);
    }

    @Bean
    public MessageBuilder messageBuilder(Session session){
        return new MessageBuilder(session);
    }

    @Bean
    public SenderService senderService(MessageBuilder messageBuilder) {
        return new SenderService(messageBuilder);
    }

    @Bean
    public FileEmployeeRepository fileEmployeeRepository() {
        return new FileEmployeeRepository("employee_data.txt");
    }

    @Bean
    public BirthdayService birthdayService(FileEmployeeRepository fileEmployeeRepository, SenderService senderService) {
        return new BirthdayService(fileEmployeeRepository, senderService);
    }

}
