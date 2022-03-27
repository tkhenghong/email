package com.awpghost.email.services.email;

import com.awpghost.email.dto.requests.SendEmailRequest;
import com.awpghost.email.dto.responses.SendEmailResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmailService {
    Mono<List<SendEmailResponse>> sendEmail(SendEmailRequest sendEmailRequest);
}
