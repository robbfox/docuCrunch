package com.sparta.docucrunchbackend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class testService {


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
        headers.set("Authorization", "Bearer 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");

        // Create the request body
        JSONObject json = new JSONObject();
        json.put("inputs", text);

        // Create the HTTP entity with the headers and body
        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        // Make the POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Extract the summary from the response
        JSONObject responseBody = new JSONObject(response.getBody());
        return responseBody.getJSONArray("summary_text").getString(0);
    }
}
