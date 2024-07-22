package com.sparta.docucrunchbackend.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Ai21StudioService {

    private static final Logger logger = LoggerFactory.getLogger(Ai21StudioService.class);

    @Value("${ai21.studio.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public Ai21StudioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String summariseText(String text) {
        String url = "https://api.ai21.com/studio/v1/summarize";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);

        JSONObject requestBody = new JSONObject();
        requestBody.put("source", text);
        requestBody.put("sourceType", "TEXT");

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            logger.info("Response status: {}", response.getStatusCode());
            logger.info("Response headers: {}", response.getHeaders());
            logger.info("Response body: {}", response.getBody());

            return handleResponse(response.getBody());

        } catch (Exception e) {
            logger.error("Failed to summarise text: {}", text, e);
            throw new RuntimeException("Failed to summarise text", e);
        }
    }

    private String handleResponse(String responseBodyString) {
        try {
            JSONObject responseBody = new JSONObject(responseBodyString);
            return responseBody.optString("summary", "No summary text found");

        } catch (Exception e) {
            logger.error("Failed to parse response body as JSON: {}", responseBodyString, e);
            throw new RuntimeException("Failed to parse response body as JSON", e);
        }
    }
}