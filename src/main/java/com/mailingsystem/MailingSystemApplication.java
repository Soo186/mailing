package com.mailingsystem;

import com.mailingsystem.mail.config.GmailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //  비동기 처리 활성화
@EnableConfigurationProperties(GmailProperties.class)
public class MailingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingSystemApplication.class, args);
    }

}
