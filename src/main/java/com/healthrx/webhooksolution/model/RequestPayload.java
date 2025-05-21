package com.healthrx.webhooksolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestPayload {
    private String name;
    private String regNo;
    private String email;
}
