package com.awpghost.email.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendEmailResponse {
    private String email;
    private String status;
}
