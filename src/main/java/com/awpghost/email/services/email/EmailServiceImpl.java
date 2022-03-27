package com.awpghost.email.services.email;

import com.awpghost.email.dto.requests.SendEmailRequest;
import com.awpghost.email.dto.responses.SendEmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Mono<List<SendEmailResponse>> sendEmail(SendEmailRequest sendEmailRequest) {
        Map<String, SendEmailResponse> sendEmailResponses = new ConcurrentHashMap<>();
        return Mono.fromCallable(() -> {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            // TODO: Use Spring Thymeleaf template engine
            sendEmailRequest.getReceiverList().forEach(receiverEmailAddress -> {
                simpleMailMessage.setTo(receiverEmailAddress);
                simpleMailMessage.setSubject(sendEmailRequest.getEmailSubject());
                simpleMailMessage.setText(sendEmailRequest.getEmailContent());
                javaMailSender.send(simpleMailMessage);

                sendEmailResponses.put(receiverEmailAddress, SendEmailResponse.builder()
                                .email(receiverEmailAddress)
                                .status("success")
                        .build());
            });

            return new ArrayList<>(sendEmailResponses.values());
        });
    }
}
