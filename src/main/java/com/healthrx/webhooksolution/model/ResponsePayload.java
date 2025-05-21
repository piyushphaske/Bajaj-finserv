package com.healthrx.webhooksolution.model;

import lombok.Data;

@Data
public class ResponsePayload {
    private String webhookUrl;
    private String accessToken;
}
