package com.awpghost.email.controllers;

import com.awpghost.email.dto.requests.SendEmailRequest;
import com.awpghost.email.dto.responses.SendEmailResponse;
import com.awpghost.email.services.email.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Send email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent successfully"),
    })
    @PostMapping("/send")
    public Mono<List<SendEmailResponse>> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) {
        return emailService.sendEmail(sendEmailRequest);
    }
}
