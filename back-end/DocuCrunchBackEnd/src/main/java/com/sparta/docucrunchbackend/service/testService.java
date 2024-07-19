package com.sparta.docucrunchbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class testService {

    private static final Logger logger = LoggerFactory.getLogger(testService.class);

    @Value("${huggingface.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    @Autowired
    public testService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String summariseText(String text) {
        String url = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiToken);

        // Create the request body
        JSONObject json = new JSONObject();
        json.put("inputs", text);

        // Create the HTTP entity with the headers and body
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        // Make the POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Log response details for debugging
        logger.info("Response status: {}", response.getStatusCode());
        logger.info("Response headers: {}", response.getHeaders());
        String responseBodyString = response.getBody();
        logger.info("Response body: {}", responseBodyString);

        try {
            // Preprocess the response to correct JSON format issues
            responseBodyString = preprocessResponse(responseBodyString);

            // Extract the summary from the response
            JSONArray responseBodyArray = new JSONArray(responseBodyString);
            JSONObject responseBody = responseBodyArray.getJSONObject(0);
            return responseBody.getString("summary_text");
        } catch (Exception e) {
            logger.error("Failed to parse response body as JSON: {}", responseBodyString, e);
            throw new RuntimeException("Failed to parse response body as JSON", e);
        }
    }

    private String preprocessResponse(String response) {
        // This method preprocesses the response to correct JSON format issues
        // For this specific case, we might need to correct quotes and structure
        // Implement specific corrections as needed for your API's response format

        // Example of replacing single quotes with double quotes and fixing JSON structure
        response = response.replace("'", "\"");

        // Further corrections might be needed based on the actual response pattern
        return response;
    }
}
