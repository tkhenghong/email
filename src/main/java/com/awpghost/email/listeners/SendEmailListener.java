package com.awpghost.email.listeners;

import com.awpghost.email.dto.requests.SendEmailRequest;
import com.awpghost.email.services.email.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SendEmailListener {
    private final EmailService emailService;

    @Autowired
    public SendEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "send_email", groupId = "email")
    public void listen(SendEmailRequest sendEmailRequest) {
        log.info("Send email request received.");
        emailService.sendEmail(sendEmailRequest);
    }
}
