package com.sparta.docucrunchbackend.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HuggingFaceService {

    private static final Logger logger = LoggerFactory.getLogger(HuggingFaceService.class);

    @Value("${huggingface.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public HuggingFaceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String summariseText(String text) {
        String url = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);

        // Create request payload
        JSONObject json = new JSONObject();
        json.put("inputs", text);
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        try {
            // Make the API request
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            logger.info("Response status: {}", response.getStatusCode());
            logger.info("Response headers: {}", response.getHeaders());
            String responseBodyString = response.getBody();
            logger.info("Response body: {}", responseBodyString);

            // Handle API response
            return handleResponse(responseBodyString);

        } catch (Exception e) {
            logger.error("Failed to summarize text: {}", text, e);
            throw new RuntimeException("Failed to summarize text", e);
        }
    }

    private String handleResponse(String responseBodyString) {
        try {
            // Ensure the response is in JSON format
            JSONArray responseBodyArray = new JSONArray(responseBodyString);

            // Check if the array is not empty
            if (responseBodyArray.length() > 0) {
                JSONObject responseBody = responseBodyArray.getJSONObject(0);
                return responseBody.optString("summary_text", "No summary text found");
            } else {
                return "No summary text found";
            }

        } catch (Exception e) {
            logger.error("Failed to parse response body as JSON: {}", responseBodyString, e);
            throw new RuntimeException("Failed to parse response body as JSON", e);
        }
    }
}
