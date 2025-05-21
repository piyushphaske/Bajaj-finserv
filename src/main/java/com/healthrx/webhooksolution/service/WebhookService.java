package com.healthrx.webhooksolution.service;

import com.healthrx.webhooksolution.model.RequestPayload;
import com.healthrx.webhooksolution.model.ResponsePayload;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        try {
            // Step 1: POST request to generate webhook
            String generateUrl = "https://bfhldeavpigu.healthrx.co.in/hiring/generateWebhook/JAVA";
            RequestPayload payload = new RequestPayload("John Doe", "REG12347", "john@example.com");

            ResponseEntity<ResponsePayload> response = restTemplate.postForEntity(generateUrl, payload, ResponsePayload.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String webhookUrl = response.getBody().getWebhookUrl();
                String jwt = response.getBody().getAccessToken();
                log.info("Webhook URL: {}", webhookUrl);

                // Step 2: Solve the assigned SQL problem manually (download and review Google Drive link)

                // Hardcoded sample SQL (replace with real one)
                String finalQuery = "SELECT * FROM users WHERE status = 'active';";

                // Step 3: Send final SQL query
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(jwt);

                HttpEntity<Map<String, String>> entity = new HttpEntity<>(Map.of("finalQuery", finalQuery), headers);

                ResponseEntity<String> submitResponse = restTemplate.postForEntity(webhookUrl, entity, String.class);

                log.info("Submission Response: {}", submitResponse.getBody());
            }

        } catch (Exception e) {
            log.error("Error in process: ", e);
        }
    }
}
